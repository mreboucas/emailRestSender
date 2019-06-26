
package br.com.mreboucas.emailRest.rest.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 * @author Marcelo Rebouças 19 de dez de 2017 - 09:35:17 [marceloreboucas10@gmail.com]
 */
public final class ListaUtils {

	/**
	 * @author Marcelo Rebouças Aug 31, 2015 - 9:59:40 AM
	 * @param collection
	 * @return String
	 */
	public static String convertListToStringSeparadaPorVirgula(Collection<?> collection) {

		return convertListToStringSeparadaPorCaractere(collection, ",");
	}
	
	private static String convertListToStringSeparadaPorCaractere(Collection<?> collection, String caractere) {

		if (collectionIsNotNullOrIsNotEmpty(collection)) {

			return StringUtils.join(collection, StringUtils.isBlank(caractere) ? "," : caractere);
		}

		return "";
	}
	
	/**
	 * @author Marcelo Rebouças Oct 20, 2014 - 3:09:42 PM
	 * @param collection
	 * @return Boolean
	 */
	@SuppressWarnings("rawtypes")
	public static Boolean collectionIsNotNullOrIsNotEmpty(Collection collection) {
		
		return collection != null && !collection.isEmpty() ? Boolean.TRUE : Boolean.FALSE;
		
	}
	
	/**
	 * @author Marcelo Rebouças Nov 10, 2015 - 2:51:35 PM
	 * @param collection void
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void removeDuplicatesElementsFromList(List<?> collection) {

		if (collectionIsNotNullOrIsNotEmpty(collection)) {

			Set set = new HashSet();

			set.addAll(collection);

			collection.clear();

			collection.addAll(set);

		}
	}
}