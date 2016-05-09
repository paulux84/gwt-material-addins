package gwt.material.design.addins.client.datetimepicker;

import com.google.gwt.core.client.JsDate;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import gwt.material.design.addins.client.MaterialResourceInjector;
import gwt.material.design.client.base.HasError;
import gwt.material.design.client.base.HasPlaceholder;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.ui.MaterialTextBox;

import java.util.Date;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

//@formatter:off

/**
 * Material Time Picker - provide a simple way to select a single value from a pre-determined set.
 *
 * <h3>XML Namespace Declaration</h3>
 * <pre>
 * {@code
 * xmlns:ma='urn:import:gwt.material.design.addins.client'
 * }
 * </pre>
 *
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code <ma:timepicker.MaterialTimePicker placeholder="Time Arrival" />}
 * </pre>
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#timepickers">Material Pickers</a>
 * @author kevzlou7979
 * @author Ben Dol
 */
//@formatter:on
public class MaterialDateTimePicker extends MaterialWidget implements HasError, HasPlaceholder, HasValue<Date> {

    static {
        if(MaterialResourceInjector.isDebug()) {
            MaterialResourceInjector.injectDebugJs(MaterialDateTimePickerDebugClientBundle.INSTANCE.momentJsDebug());
            MaterialResourceInjector.injectDebugJs(MaterialDateTimePickerDebugClientBundle.INSTANCE.dateTimepickerJsDebug());
            MaterialResourceInjector.injectCss(MaterialDateTimePickerDebugClientBundle.INSTANCE.dateTimepickerCssDebug());
        } else {
            MaterialResourceInjector.injectJs(MaterialDateTimePickerClientBundle.INSTANCE.momentJs());
            MaterialResourceInjector.injectJs(MaterialDateTimePickerClientBundle.INSTANCE.dateTimepickerJs());
            MaterialResourceInjector.injectCss(MaterialDateTimePickerClientBundle.INSTANCE.dateTimepickerCss());
        }
    }

    private MaterialTextBox textBox = new MaterialTextBox();
    private Element dateTimePickerInputElement;

    /** The current value held by the time picker. */
    private Date time;

    //region default param
    private String format = "D MMM YY [at] HH:mm";
    private boolean shortTime = false;
    private boolean showDate = true;
    private boolean showTime = true;
    private boolean clearButton = false;
    private boolean nowButton = true;
    private boolean switchOnClick = false;
    private String cancelText = "Cancel";
    private String okText = "Ok";
    private String clearText = "Clear";
    private String nowText = "Now";
    private boolean timePickerInit=false;
    private String placeholder;
    //endregion


    public MaterialDateTimePicker() {
        super(Document.get().createDivElement());
        dateTimePickerInputElement = textBox.asGwtValueBoxBase().getElement();
        this.add(this.textBox);
        this.initTimePicker();
    }

    @Override
    protected void onLoad() {
        super.onLoad();
    }

    /**
     * Side effects:
     * <ul>
     *   <li>Resets the time to <i>now<i></li>
     *   <li>Clears erros/success message</li>
     * </ul>
     */
    public void reset() {
        this.setValue(new Date());
        this.clearErrorOrSuccess();
    }

    /**
     * @return The placeholder text.
     */
    @Override
    public String getPlaceholder() {
        return this.textBox.getPlaceholder();
    }

    @Override
    public void addStyleName(String style) {
        textBox.addStyleName(style);
    }

    @Override
    public void setStyleName(String style) {
        textBox.setStyleName(style);
    }

    /**
     * @param placeholder
     *            The placeholder text to set.
     */
    @Override
    public void setPlaceholder(String placeholder) {
        textBox.setPlaceholder(placeholder);
    }

    @Override
    public void setError(String error) {
        this.textBox.setError(error);
    }

    @Override
    public void setSuccess(String success) {
        this.textBox.setSuccess(success);
    }

    @Override
    public void clearErrorOrSuccess() {
        this.textBox.clearErrorOrSuccess();
    }

    public void initTimePicker() {
        this.initTimePicker(this.dateTimePickerInputElement);
        timePickerInit=true;
    }

    protected native void initTimePicker(Element e) /*-{
        var that = this;
        $wnd.jQuery(document).ready(function() {
            var dateTimePicker = $wnd.jQuery(e).bootstrapMaterialDatePicker({
                format : that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::getFormat()(),
                shortTime : that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::isShortTime()(),
                date : that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::isShowDate()(),
                time : that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::isShowTime()(),
                clearButton : that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::isClearButton()(),
                nowButton : that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::isNowButton()(),
                switchOnClick : that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::isSwitchOnClick()(),
                cancelText : that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::getCancelText()(),
                okText : that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::getOkText()(),
                clearText : that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::getClearText()(),
                nowText : that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::getNowText()(),
            });

            dateTimePicker.on('change', function(e, date){
                that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::onChange(*)(date.toDate());
            });
            dateTimePicker.on('beforeChange', function(e, date){
                that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::onBeforeChange(*)(date.toDate());
            });
            dateTimePicker.on('dateSelected', function(e, date){
                that.@gwt.material.design.addins.client.datetimepicker.MaterialDateTimePicker::onDateSelected(*)(date.toDate());
            });

        });
    }-*/;


    //region getter and setter

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public boolean isShortTime() {
        return shortTime;
    }

    public void setShortTime(boolean shortTime) {
        this.shortTime = shortTime;
    }

    public boolean isShowDate() {
        return showDate;
    }

    public void setShowDate(boolean showDate) {
        this.showDate = showDate;
    }

    public boolean isShowTime() {
        return showTime;
    }

    public void setShowTime(boolean showTime) {
        this.showTime = showTime;
    }

    public boolean isClearButton() {
        return clearButton;
    }

    public void setClearButton(boolean clearButton) {
        this.clearButton = clearButton;
    }

    public boolean isNowButton() {
        return nowButton;
    }

    public void setNowButton(boolean nowButton) {
        this.nowButton = nowButton;
    }

    public boolean isSwitchOnClick() {
        return switchOnClick;
    }

    public void setSwitchOnClick(boolean switchOnClick) {
        this.switchOnClick = switchOnClick;
    }

    public String getCancelText() {
        return cancelText;
    }

    public void setCancelText(String cancelText) {
        this.cancelText = cancelText;
    }

    public String getOkText() {
        return okText;
    }

    public void setOkText(String okText) {
        this.okText = okText;
    }

    public String getClearText() {
        return clearText;
    }

    public void setClearText(String clearText) {
        this.clearText = clearText;
    }

    public String getNowText() {
        return nowText;
    }

    public void setNowText(String nowText) {
        this.nowText = nowText;
    }

    //endregionget

    //region events
    protected void onBeforeChange(JsDate date){

    }

    protected void onChange(JsDate date){
        this.time = new Date((long) date.getTime());
        this.fireValueChangeEvent();
    }

    protected void onDateSelected(JsDate date){

    }
    //endregion


    @Override
    public void setEnabled(boolean enabled) {
        this.textBox.setEnabled(enabled);
    }
    
    private void fireValueChangeEvent() {
        ValueChangeEvent.fire(this, this.time);
    }

    @Override
    public void clear() {
        this.clearTimePickerValue(this.dateTimePickerInputElement);
    }

    private native void clearTimePickerValue(Element e) /*-{
        $wnd.jQuery(e).val('');
    }-*/;
    
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<Date> handler) {
        return this.addHandler(handler, ValueChangeEvent.getType());
    }

    @Override
    public Date getValue() {
        return this.time;
    }

    private static native JsDate getUtcDateTime(String dateTimeString, String format)/*-{
        return $wnd.moment(dateTimeString, format).utc().toDate();
    }-*/;

    private static native String formatDate(JsDate date, String format)/*-{
        return $wnd.moment(date).format(format);
    }-*/;

    private static native void setDateTimePickerDate(Element e , JsDate date)/*-{
        $wnd.jQuery(e).bootstrapMaterialDatePicker('setDate', date)
    }-*/;

    private static native void setMinDate(Element e , JsDate date)/*-{
        $wnd.jQuery(e).bootstrapMaterialDatePicker('setMinDate', date)
    }-*/;

    private static native void setMaxDate(Element e , JsDate date)/*-{
        $wnd.jQuery(e).bootstrapMaterialDatePicker('setMaxDate', date)
    }-*/;


    public void setMaxDate(Date date){
        setMaxDate(dateTimePickerInputElement,convertDate(date));
    }

    public void setMinDate(Date date){
        setMinDate(dateTimePickerInputElement,convertDate(date));
    }

    public void setFromToday(boolean fromToday){
        if(fromToday)
            setMinDate(new Date());
    }


    @Override
    public void setValue(Date time) {
       this.setValue(time, true);
    }

    private JsDate convertDate(Date date){
        return JsDate.create((double) date.getTime());
    }

    @Override
    public void setValue(Date dateTime, boolean fireEvents) {
        
        if(this.time != null) {
            if(this.time.equals(dateTime)) {
                return;
            }
        }

        if(this.time == dateTime) {
            return;
        }

        this.time = dateTime;
        JsDate jsDate = convertDate(dateTime);

//        this.setValue(this.dateTimePickerInputElement, formatDate(jsDate,format));

        textBox.setText(formatDate(jsDate,format));

        if(isTimePickerInit())
            setDateTimePickerDate(this.getElement(), jsDate);

        if(fireEvents == true) {
            this.fireValueChangeEvent();
        }

    }
    
    private native void setValue(Element e, String time) /*-{
        $wnd.jQuery(e).val(time);
    }-*/;

    public boolean isTimePickerInit() {
        return timePickerInit;
    }
}
