package com.martin.component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JAXBDemo {
	Logger log= LoggerFactory.getLogger(JAXBDemo.class);
	
	public class JAXBUtil<T> {
		public String marshal(T element) throws JAXBException, IOException {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				JAXBContext jc = JAXBContext.newInstance(element.getClass());
				Marshaller m = jc.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
				m.marshal(element, os);
				String xml = new String(os.toByteArray(), "UTF-8");
				return xml;
			} finally {
				os.close();
			}
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public T unmarshal(Class c, String xml) throws JAXBException {
			JAXBContext context;
			context = JAXBContext.newInstance(c);
			Unmarshaller unmarshal = context.createUnmarshaller();
			T obj = (T) unmarshal.unmarshal(new StringReader(xml));
			return obj;

		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		public T unmarshal(Class c, InputStream is) throws JAXBException {
			JAXBContext context;
			context = JAXBContext.newInstance(c);
			Unmarshaller unmarshal = context.createUnmarshaller();
			T obj = (T) unmarshal.unmarshal(is);
			return obj;
		}
	}

	public void Test() {
		User martin=new User();
		
		martin.setAge(11);
		martin.setBirthday(new Date());
		martin.setEmail("martin@igt.com");
		martin.setName("Martin");
		log.info("Martin name:{} , age:{}, birthday:{}",martin.getName(),martin.getAge(), martin.getBirthday());
		JAXBUtil<User> util=new JAXBUtil<>();
		
		try {
			String message=util.marshal(martin);
			log.info(message);
			
			User newUser=util.unmarshal(User.class, message);
			
			log.info("UmarshalUser name:{} , age:{}, birthday:{}",newUser.getName(),newUser.getAge(), newUser.getBirthday());
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
