import org.springframework.aop.aspectj.AspectJAdviceParameterNameDiscoverer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;

public class Test {
	public static void main(String[] args) {
		// BeanFactory
		// ApplicationContext
		// BeanDefinition
		// FactoryBean<T>
		doInsertSort1(new int[]{99,33,1,2});
	}

	static void doInsertSort1(int[] src) {
		int len = src.length;
		for (int i = 1; i < len; i++) {
			int temp = src[i];
			int j = i;

			while (src[j - 1] > temp) {
				src[j] = src[j - 1];
				j--;
				if (j <= 0)
					break;
			}
			src[j] = temp;
			print(i, src);
		}
	}
	
	static void print(int i, int[] src) {
		System.out.println("----------------------- " + i);
		for (int j = 0; j < src.length; j++) {
			System.out.println(j);
		}
	}
}
