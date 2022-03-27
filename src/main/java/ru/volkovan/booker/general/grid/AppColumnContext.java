package ru.volkovan.booker.general.grid;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import ru.volkovan.booker.general.util.AppReflectionUtil;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class AppColumnContext {

    private static final Map<Class<?>, Map<String, ColumnDefinition>>
            columnDefinitionByFieldNameByBeanType = new ConcurrentHashMap<>();

    public static Map<String, ColumnDefinition> getColumnDefinitionByFieldName(Class<?> beanType) {
        return columnDefinitionByFieldNameByBeanType.computeIfAbsent(beanType, (key) -> {
            Map<String, ColumnDefinition> columnDefinitionByFieldName = new HashMap<>();

            Arrays.stream(key.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(AppColumn.class))
                    .forEach(field -> storeIfUniqueName(asDefinition(field, beanType), columnDefinitionByFieldName));

            Arrays.stream(key.getDeclaredMethods())
                    .filter(method -> method.isAnnotationPresent(AppColumn.class))
                    .forEach(method -> storeIfUniqueName(asDefinition(method, beanType), columnDefinitionByFieldName));

            return columnDefinitionByFieldName.values().stream()
                    .sorted(Comparator.comparing(o -> o.order))
                    .collect(
                            LinkedHashMap::new,
                            (map, item) -> map.put(item.fieldName, item),
                            Map::putAll
                    );
        });
    }

    private static <T extends AccessibleObject & Member> ColumnDefinition asDefinition(T source, Class<?> beanType) {
        AppColumn columnAnnotation = source.getAnnotation(AppColumn.class);

        String fieldName = source.getName();

        Method getter = source.getClass().equals(Method.class) ?
                (Method) source :
                AppReflectionUtil.getMethodByName("get".concat(StringUtils.capitalize(fieldName)), beanType);

        String label = columnAnnotation.label();
        int order = columnAnnotation.order();
        String width = columnAnnotation.width();

        Class<? extends FieldAdapter> adapter = null;
        AppColumn.Adapter adapterAnnotation = source.getAnnotation(AppColumn.Adapter.class);
        if (adapterAnnotation != null) {
            adapter = adapterAnnotation.value();
        }

        return new ColumnDefinition(fieldName, getter, label, order, width, adapter, beanType);
    }

    private static void storeIfUniqueName(ColumnDefinition definition, Map<String, ColumnDefinition> columnDefinitionByFieldName) {
        String fieldName = definition.fieldName;
        if (columnDefinitionByFieldName.containsKey(fieldName)) {
            throw new RuntimeException(
                    "dublicate field name '%s' in class %".formatted(definition.fieldName, definition.sourceClass.getSimpleName())
            );
        }
        columnDefinitionByFieldName.put(fieldName, definition);
    }

    public record ColumnDefinition(String fieldName, Method getter,
                                   String label, Integer order, String width,
                                   Class<? extends FieldAdapter> adapter,
                                   Class<?> sourceClass) {
    }
}
