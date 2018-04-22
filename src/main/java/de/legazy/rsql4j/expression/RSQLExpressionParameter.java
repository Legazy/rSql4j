package main.java.de.legazy.rsql4j.expression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import main.java.de.legazy.rsql4j.annotation.RSQLPropertyName;
import main.java.de.legazy.rsql4j.exception.RSQLPropertyNotFoundException;

public class RSQLExpressionParameter<T> {

	private T object;
	
//	@Inject 
//	@JbossLogger
//	private Logger logger;

	public RSQLExpressionParameter(T object) {
		this.object = object;
	}

	public <V> V get(String property) throws RSQLPropertyNotFoundException {

		V value = null;

		try {
			if (isObjectReference(property)) {
				List<String> properties = Arrays.asList(property.split(Pattern.quote(".")));
				value = internalGet(properties);
			} else {
				value = internalGet(property);
			}

		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {

		}

		return value;

	}

	@SuppressWarnings("unchecked")
	private <V> V internalGet(String propertyName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, RSQLPropertyNotFoundException {

		Method method = null;

		method = searchMethod(propertyName, this.object.getClass());

		return (V) method.invoke(this.object);

	}

	@SuppressWarnings("unchecked")
	private <V> V internalGet(List<String> propertyNames) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, RSQLPropertyNotFoundException  {

		Method method = null;
		Object object = this.object;

		for (String propertyName : propertyNames) {
			
			if(Optional.ofNullable(object).isPresent()) {
				method = searchMethod(propertyName, object.getClass());

				if (!(propertyNames.indexOf(propertyName) == propertyNames.size() - 1))
					object = method.invoke(object);
			}
			else 
				return null;
		}

		return (V) method.invoke(object);

	}

	private Boolean isObjectReference(String property) {
		return property.contains(".");
	}

	private Method searchMethod(String propertyName, Class<?> clazz) throws RSQLPropertyNotFoundException {

		Optional<Method> optionalMethod = Optional.empty();
		
		while (!optionalMethod.isPresent()) {
			
			Optional.ofNullable(clazz).orElseThrow(() -> new RSQLPropertyNotFoundException());
			
			optionalMethod = Arrays.asList(clazz.getMethods()).stream().filter(x -> { 
				 Optional<RSQLPropertyName> annotation = Arrays.asList(x.getAnnotationsByType(RSQLPropertyName.class)).stream().findFirst();
				 return annotation.isPresent() && annotation.get().value().equals(propertyName) && annotation.get().isAccessable() == true;
			}).findFirst();
			
			if(!optionalMethod.isPresent()) 
				clazz = clazz.getSuperclass();
		}
		
		return optionalMethod.orElse(null);
		
	}

}
