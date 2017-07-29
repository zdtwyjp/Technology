package org.tech.kafka.demo2;


import kafka.api.FetchRequest;  
import kafka.api.FetchRequestBuilder;  
import kafka.api.PartitionOffsetRequestInfo;  
import kafka.common.ErrorMapping;  
import kafka.common.TopicAndPartition;  
import kafka.javaapi.*;  
import kafka.javaapi.consumer.SimpleConsumer;  
import kafka.message.MessageAndOffset;  
 

import java.nio.ByteBuffer;  
import java.util.ArrayList;  
import java.util.Collections;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  

import org.tech.kafka.KafkaProperties;
 
/*�ٷ��ĵ��ķ��룺 
*һ�������ʹ��high level consumer���ɣ����������ζ����� 
*ʹ��simpleconsumer���龰�� 
*      �������ͬһ�����ݣ� 
*      ��һ�����������ֻ����ĳ��topic���ַ��������ݣ� 
*      �Լ�д���뱣֤ÿһ����Ϣ��������һ�� 
*ʹ�øýӿ���Ҫ�Ķ��⹤���� 
*      �Լ�׷�����ѵ�offset�� 
*      �ҳ�һ��topicÿһ��������leader broker 
*      �Լ�����leader broker�ı�Ǩ��ת�� 
*ʹ�ò��裺 
*      �ҳ���Ҫ���ѵ�topic������leader broker 
*      �ҳ�topic��partition��replica broker 
*      ����request����װ�������Ȥ�����ݣ���ָ��topic��partition����Ϣ 
*      ��ȡ���� 
*      Ӧ��broker�ı仯 
* 
*��򵥵ķ����ǣ�ͨ�������ļ����������д��Σ�����broker����Ϣ�� 
*��Ȼ����Ҫ�����е�broker�б�һ����live�ļ��ɣ�ͨ�������ҵ�leader 
**/  
public class SimpleConsumerDemo2 {  
   // ��Ա����  
   private List<String> m_replicaBrokers = new ArrayList<String>();  
 
   // ���캯��  
   public SimpleConsumerDemo2() {  
       m_replicaBrokers = new ArrayList<String>();  
   }  
   
   public static void main(String args[]) {  
       SimpleConsumerDemo2 example = new SimpleConsumerDemo2();  
       long maxReads = 100l;  
       String topic = KafkaProperties.TOPIC2;  
       int partition = 0;  
       List<String> seeds = new ArrayList<String>();  
       seeds.add("192.168.1.15");  
       int port = 9092;
       try {  
           example.run(maxReads, topic, partition, seeds, port);  
       } catch (Exception e) {  
           System.out.println("Oops:" + e);  
           e.printStackTrace();  
       }  
   }  
 
   public void run(long a_maxReads, String a_topic, int a_partition, List<String> a_seedBrokers, int a_port)  
           throws Exception {  
       //find the meta data about the topic and partition we are interested in  
       //�ҵ�ָ��topic��ָ��������Ԫ��Ϣ����������  
       PartitionMetadata metadata = findLeader(a_seedBrokers, a_port, a_topic, a_partition);  
       if (metadata == null) {  
           System.out.println("Can't find metadata for Topic and Partition. Exiting");  
           return;  
       }  
       if (metadata.leader() == null) {  
           System.out.println("Can't find Leader for Topic and Partition. Exiting");  
           return;  
       }  
       //��topic�ĸ÷�����leader broker  
       String leadBroker = metadata.leader().host();  
       String clientName = "Client_" + a_topic + "_" + a_partition;  
 
       //��������е�brokerĬ��ʹ����ͬһ��port��???  
       SimpleConsumer consumer = new SimpleConsumer(leadBroker, a_port, 100000, 64 * 1024, clientName);  
       //���ʼ��������  
       long readOffset = getLastOffset(consumer, a_topic, a_partition, kafka.api.OffsetRequest.EarliestTime(),  
               clientName);  
       System.out.println(readOffset);  
       int numErrors = 0;  
       while (a_maxReads > 0) {  
           if (consumer == null) {  
               consumer = new SimpleConsumer(leadBroker, a_port, 100000, 64 * 1024, clientName);  
           }  
           //Note: this fetchSize of 100000 might need to be increased if large batches are written to Kafka  
           FetchRequest req = new FetchRequestBuilder().clientId(clientName)  
                   .addFetch(a_topic, a_partition, readOffset, 100000)   
                   .build();  
           FetchResponse fetchResponse = consumer.fetch(req);  
 
           /*simpleconsumer�Լ�û�д���leader broker�����������������Ҫ�Լ����� 
            **/  
           if (fetchResponse.hasError()) {  
               numErrors++;  
               // Something went wrong!  
               //�õ��������  
               short code = fetchResponse.errorCode(a_topic, a_partition);  
               System.out.println("Error fetching data from the Broker:" + leadBroker + " Reason: " + code);  
               //�ۼƹ�5�δ��󣬽���whileѭ����������Ϣ��  
               if (numErrors > 5)  
                   break;  
               //���������offset������Ч��Χ.��offset��Ч����򵥵Ľ����������ȡ���µ�offsetֵ�������µ���Ϣ  
               if (code == ErrorMapping.OffsetOutOfRangeCode()) {  
                   // We asked for an invalid offset. For simple case ask for  
                   // the last element to reset  
                   readOffset = getLastOffset(consumer, a_topic, a_partition, kafka.api.OffsetRequest.LatestTime(),  
                           clientName);  
                   continue;  
               }  
               consumer.close();  
               consumer = null;  
               //����������ֱ�ӹر�consumer����һ���µ�leader����������  
               leadBroker = findNewLeader(leadBroker, a_topic, a_partition, a_port);  
               continue;  
           }//if error  
           //û�д���ֱ����������  
           numErrors = 0;  
 
           long numRead = 0;  
           //��ȡ��Ϣ  
           for (MessageAndOffset messageAndOffset : fetchResponse.messageSet(a_topic, a_partition)) {  
               long currentOffset = messageAndOffset.offset();  
               //���ѵ���offset���ȸտ�ʼ�����С����Ϊ�Ǵ�readOffset��ʼ���ѵģ�ÿ����һ����currentOffset++  
               if (currentOffset < readOffset) {  
                   System.out.println("Found an old offset: " + currentOffset + " Expecting: " + readOffset);  
                   continue;  
               }  
               readOffset = messageAndOffset.nextOffset();  
               //��Ϣ�ĸ���  
               ByteBuffer payload = messageAndOffset.message().payload();  
 
               byte[] bytes = new byte[payload.limit()];  
               payload.get(bytes);  
               System.out.println(String.valueOf(messageAndOffset.offset()) + ": " + new String(bytes, "UTF-8"));  
               numRead++;  
               a_maxReads--;  
           }  
 
           if (numRead == 0) {  
               try {  
                   Thread.sleep(1000);  
               } catch (InterruptedException ie) {  
               }  
           }  
       }//while  
       if (consumer != null)  
           consumer.close();  
   }  
 
   /*����������һ��topic��ָ��partitionʱ�������￪ʼ������ 
    *kafka.api.OffsetRequest.EarliestTime()�ҵ���־�����ݵ��ʼͷλ�ã������￪ʼ���ѣ�hadoop-consumer��ʹ�õ�Ӧ�þ������ַ�ʽ�� 
    *kafka.api.OffsetRequest.LatestTime()ֻ�������µ����� 
    *ע�⣬��Ҫ����0��offset�ĳ�ʼֵ 
    *������long whichTime��ȡֵ�����֣� 
    *                          kafka.api.OffsetRequest.LatestTime() 
    *                          kafka.api.OffsetRequest.LatestTime() 
    *����ֵ��һ��long���͵�offset*/  
   public static long getLastOffset(SimpleConsumer consumer, String topic, int partition, long whichTime,  
           String clientName) {  
       //topic�Ͷ�Ӧ��partition��װ����һ������һ��topic�кö�������Ķ���  
       TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);  
       Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();  
       requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));  
       /*ͬ����request��response 
        *��ȡ���topic��ָ��������offset��Ϣ*/  
       kafka.javaapi.OffsetRequest request = new kafka.javaapi.OffsetRequest(requestInfo,  
               kafka.api.OffsetRequest.CurrentVersion(), clientName);  
       OffsetResponse response = consumer.getOffsetsBefore(request);  
 
       if (response.hasError()) {  
           System.out.println(  
                   "Error fetching data Offset Data the Broker. Reason: " + response.errorCode(topic, partition));  
           return 0;  
       }  
       long[] offsets = response.offsets(topic, partition);  
       return offsets[0];  
   }  
 
   /*ÿһ��topic�Ĳ�ͬ�����в�ͬ��leader 
    **/  
   private String findNewLeader(String a_oldLeader, String a_topic, int a_partition, int a_port) throws Exception {  
       for (int i = 0; i < 3; i++) {  
           boolean goToSleep = false;  
           PartitionMetadata metadata = findLeader(m_replicaBrokers, a_port, a_topic, a_partition);  
           if (metadata == null) {  
               goToSleep = true;  
           } else if (metadata.leader() == null) {  
               goToSleep = true;  
           } else if (a_oldLeader.equalsIgnoreCase(metadata.leader().host()) && i == 0) {  
               /*��һ���ҳ�����leader�;�leaderһ������ô��zookeeperһ���Ļָ�ʱ�� 
                *first time through if the leader hasn't changed give 
                *ZooKeeper a second to recover 
                *�ڶ��Σ��ٶ��þɵ�brokerȷʵ�Ѿ��ָ������ˣ�ͬʱ�ֱ�ѡΪleader�����߸����Ͳ���leader�����⣬ 
                *���������������������goToSleep 
                *second time, assume the broker did recover before failover, 
                *or it was a non-Broker issue*/  
               goToSleep = true;  
           } else {  
               return metadata.leader().host();//������leader����Ϣ  
           }  
           if (goToSleep) {//goToSleepΪtrue����ô��Ϣһ�ᣬ�ȴ�һ��  
               try {  
                   Thread.sleep(1000);  
               } catch (InterruptedException ie) {  
               }  
           }  
       }//for ���������𣿣�  
       //����֮���Զ�����  
       System.out.println("Unable to find new leader after Broker failure. Exiting");  
       throw new Exception("Unable to find new leader after Broker failure. Exiting");  
   }  
 
   /*Ѱ��leader��ͨ�������л��������ļ�����һ��broker���б� 
    *����Ҫ�����е�broker��ֻ��Ҫ�����п��Ի��һ��live broker�Բ�ѯleader����Ϣ���� 
    *������ a_seedBrokers����֪��broker���б� 
    *����ֻ��һ���˿ںţ��кö�broker������ 
    *����ֵ��ָ��������Ԫ���ݣ�Ӧ�ð����÷�����leader�Լ���Ϣ��Ŀ�� 
    */  
   private PartitionMetadata findLeader(List<String> a_seedBrokers, int a_port, String a_topic, int a_partition) {  
       PartitionMetadata returnMetaData = null;  
       loop: for (String seed : a_seedBrokers) {  
           SimpleConsumer consumer = null;  
           try {  
               /*simpleConsumer�Ĺ��캯��������broker��IP��port��soTimeout��bufferSize��clientId 
                *hadoop-consumer���Ǵ������ļ��ж�ȡ������������soTimeout��bufferSize*/  
               consumer = new SimpleConsumer(seed, a_port, 100000, 64 * 1024, "leaderLookup");  
               List<String> topics = Collections.singletonList(a_topic);  
               //��ȡtopic��Ԫ����  
               TopicMetadataRequest req = new TopicMetadataRequest(topics);  
               kafka.javaapi.TopicMetadataResponse resp = consumer.send(req);  
 
               List<TopicMetadata> metaData = resp.topicsMetadata();  
               for (TopicMetadata item : metaData) {  
                   /*��topic��Ԫ�����л�ȡpartition��Ԫ���� 
                    *topic��Ԫ������Ӧ�ÿ��Ի�֪��topic�ж��ٸ����� 
                    *int partition_num = item.partitionsMetadata().size();*/  
                   for (PartitionMetadata part : item.partitionsMetadata()) {  
                       if (part.partitionId() == a_partition) {  
                           returnMetaData = part;  
                           break loop;  
                       }  
                   }  
               }  
           } catch (Exception e) {  
               System.out.println("Error communicating with Broker [" + seed + "] to find Leader for [" + a_topic  
                       + ", " + a_partition + "] Reason: " + e);  
           } finally {  
               if (consumer != null)  
                   consumer.close();  
           }  
       }  
       if (returnMetaData != null) {  
           m_replicaBrokers.clear();  
           for (kafka.cluster.BrokerEndPoint replica : returnMetaData.replicas()) {  
               m_replicaBrokers.add(replica.host());  
           }  
       }  
       return returnMetaData;  
   }  
}  
