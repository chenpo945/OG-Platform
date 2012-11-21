/*
 * Copyright 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.views.forms.Dropdown',
    dependencies: ['og.common.util.ui.Form'],
    obj: function () {
        var module = this, id_count = 0, prefix = 'dropdown_widget_';
        return function (config) {
            var field, name = config.index, resource = config.resource, form = config.form, value = config.value,
                placeholder = config.placeholder, fields = config.fields || [0], values = fields[0],
                rest_options = config.rest_options || {}, data_generator = config.data_generator,
                texts = typeof fields[1] !== 'undefined' ? fields[1] : values, processor = config.processor,
                id = prefix + id_count++, meta = rest_options && rest_options.meta, classes = config.classes;
            var generator = function (handler) {
                var $select = $('<select/>').attr('id', id);
                var rest_handler = function (result) {
                    var empty = true;
                    if (result.error) return handler('an error occurred');
                    if (meta) {
                        result.data.types.forEach(function (datum) {
                            var $option = $('<option/>').val(datum).html(datum);
                            if (value === datum) $option[0].setAttribute('selected', 'selected');
                            $select.append($option);
                            empty = false;
                        });
                    } else {
                        result.data.data.forEach(function (datum) {
                            var fields = datum.split('|'),
                                $option = $('<option/>').val(fields[values]).html(fields[texts]);
                            if (value === fields[values]) $option[0].setAttribute('selected', 'selected');
                            $select.append($option);
                            empty = false;
                        });
                    }
                    if (empty) $select.attr('disabled', 'disabled');
                    handler($.outer($select[0]));
                };
                if (name) $select.attr('name', name);
                if (classes) $select.attr('class', classes);
                if (placeholder) $select.append($('<option value="" />').html(placeholder));
                rest_options.cache_for = rest_options.cache_for || 30 * 1000;
                if (!data_generator) return og.api.rest[resource].get(rest_options).pipe(rest_handler);
                data_generator(function (data) {
                    data.forEach(function (datum) {
                        var option = typeof datum === 'string' ? {value: datum, text: datum} : datum,
                            $option = $('<option/>').attr('value', option.value).html(option.text);
                        if (value === option.value) $option[0].setAttribute('selected', 'selected');
                        $select.append($option);
                    });
                    if (!data.length) $select.attr('disabled', 'disabled');
                    return handler($.outer($select[0]));
                });
            };
            field = new form.Field({
                generator: generator, processor: processor ? processor.partial('#' + id) : null
            });
            field.on = field.on.partial(undefined, '#' + id);
            field.off = field.off.partial(undefined, '#' + id);
            return field;
        };
    }
});