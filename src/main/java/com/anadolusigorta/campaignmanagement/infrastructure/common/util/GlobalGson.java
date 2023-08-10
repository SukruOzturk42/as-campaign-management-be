package com.anadolusigorta.campaignmanagement.infrastructure.common.util;

import com.anadolusigorta.campaignmanagement.domain.common.Constants;
import com.google.gson.*;
import lombok.NonNull;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class GlobalGson {

    private Gson gson ;

    private static final GlobalGson globalGson = new GlobalGson();

    public static GlobalGson get() {
        return globalGson;
    }

    private GlobalGson() {

    }

    public Gson getGson() {
        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());

            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());

            gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());

            gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());

            gson= gsonBuilder.setPrettyPrinting().create();
        }
        return gson;
    }

    public <T> String toJson(@NonNull final T t) {
        return getGson().toJson(t);
    }

    public <T> JsonObject getAsJsonObject(@NonNull final T t) {
        return getGson().toJsonTree(t).getAsJsonObject();
    }

    public <T> T fromJson(@NonNull final String o, @NonNull Class<T> tClass) {
        return getGson().fromJson(o, tClass);
    }

    public <T> T fromJson(@NonNull final JsonElement o, @NonNull Class<T> tClass) {
        return getGson().fromJson(o, tClass);
    }

    public <E> ArrayList<E> listFromJson(@NonNull final String o, @NonNull final Type listType) {
        return getGson().fromJson(o, listType);
    }

    public <E> ArrayList<E> listFromJson(@NonNull final JsonElement o, @NonNull final Type listType) {
        return getGson().fromJson(o, listType);
    }

    public <E> ArrayList<E> listFromJson(@NonNull final String o, @NonNull final Class<E> eClass) {
        return getGson().fromJson(o, new ListType<>(eClass));
    }

    public <E> ArrayList<E> listFromJson(@NonNull final JsonElement o, @NonNull final Class<E> eClass) {
        return getGson().fromJson(o, new ListType<>(eClass));
    }

    private static class ListType<E> implements ParameterizedType {

        private Class<?> wrapped;

        private ListType(Class<E> wrapped) {
            this.wrapped = wrapped;
        }

        public Type[] getActualTypeArguments() {
            return new Type[]{wrapped};
        }

        public Type getRawType() {
            return ArrayList.class;
        }

        public Type getOwnerType() {
            return null;
        }
    }

    class LocalDateSerializer implements JsonSerializer < LocalDate > {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);

        @Override
        public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDate));
        }
    }

    class LocalDateTimeSerializer implements JsonSerializer < LocalDateTime > {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN_SECONDS);

        @Override
        public JsonElement serialize(LocalDateTime localDateTime, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDateTime));
        }
    }

    class LocalDateDeserializer implements JsonDeserializer < LocalDate > {
        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern(Constants.DATE_PATTERN).withLocale(Locale.ENGLISH));
        }
    }

    class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern(Constants.DATE_TIME_PATTERN).withLocale(Locale.ENGLISH));
        }
    }
}
