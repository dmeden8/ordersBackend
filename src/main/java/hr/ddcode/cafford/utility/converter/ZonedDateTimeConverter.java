package hr.ddcode.cafford.utility.converter;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * <p>Converter for JSON serialization/deserialization and DB storage/retrieval</p>
 *
 * Annotate fields of type ZonedDateTime with Jackson annotations to enable defined JSON conversion
 * {@link JsonSerialize}(converter = <tt>ZonedDateTimeConverter.Serializer.class</tt>)
 * {@link JsonDeserialize}(converter = <tt>ZonedDateTimeConverter.Deserializer.class</tt>)
 */
public class ZonedDateTimeConverter {

	/**
	 * Instructs Jackson how to convert/serialize ZonedDateTime (JSON conversion)
	 */
	public static class Serializer extends StdConverter<ZonedDateTime, String> {
		@Override
		public String convert(ZonedDateTime value) {
			try {
				return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(value);
			} catch (NullPointerException ex) {
				return null;
			}
		}
	}

	/**
	 * Instructs Jackson how to convert/deserialize ZonedDateTime (JSON conversion)
	 */
	public static class Deserializer extends StdConverter<String, ZonedDateTime> {
		@Override
		public ZonedDateTime convert(String value) {
			try {
				return ZonedDateTime.parse(value, DateTimeFormatter.ISO_ZONED_DATE_TIME);
			} catch (NullPointerException ex) {
				return null;
			}
		}
	}

	/**
	 * Converts ZonedDateTime to varchar and vice-versa (Database conversion)
	 *
	 * Note: Conversion is done automatically prior to saving to/fetching from database
	 */
	@Converter(autoApply = true)
	public static class Attribute implements AttributeConverter<ZonedDateTime, String> {
		@Override
		public String convertToDatabaseColumn(ZonedDateTime attribute) {
			try {
				return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(attribute);
			} catch (NullPointerException ex) {
				return null;
			}
		}

		@Override
		public ZonedDateTime convertToEntityAttribute(String dbData) {
			try {
				return ZonedDateTime.parse(dbData, DateTimeFormatter.ISO_ZONED_DATE_TIME);
			} catch (NullPointerException ex) {
				return null;
			}
		}
	}

}

