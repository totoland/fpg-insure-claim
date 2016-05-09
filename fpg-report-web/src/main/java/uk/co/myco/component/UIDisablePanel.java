/*
 * The MIT License
 *
 * Copyright 2016 totoland.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.co.myco.component;

import java.io.IOException;
import javax.faces.component.*;
import javax.faces.context.FacesContext;

/*
 * @author Brendan Healey (Oversteer)
 */
@FacesComponent("uk.co.myco.component.UIDisablePanel")
public class UIDisablePanel extends UIComponentBase {

    private enum PropertyKeys {

        disabled;
    }

    public UIDisablePanel() {
        setRendererType(null);
    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {

        boolean toDisable = isDisabled();
        processDisablePanel(this, toDisable);
        //super.encodeBegin(context);
    }

    public void processDisablePanel(UIComponent root, boolean toDisable) {

        /*
         * The key point here is that a child component of <x:disablePanel> may
         * already be disabled, in which case we don't want to enable it if the
         * <x:disablePanel disabled= attribute is set to true.
         */
        for (UIComponent c : root.getChildren()) {
            if (c instanceof UIInput || c instanceof UICommand) {
                if (toDisable) { // <x:disablePanel disabled="true">
                    Boolean curState = (Boolean) c.getAttributes().get("disabled");
                    if (curState == null || curState == false) {
                        c.getAttributes().put("UIPanelDisableFlag", true);
                        c.getAttributes().put("disabled", true);
                    }
                } else { // <x:disablePanel disabled="false">
                    if (c.getAttributes().get("UIPanelDisableFlag") != null) {
                        c.getAttributes().remove("UIPanelDisableFlag");
                        c.getAttributes().put("disabled", false);
                    }
                }
            }

            if (c.getChildCount() > 0) {
                processDisablePanel(c, toDisable);
            }
        }

    }

    @Override
    public String getFamily() {
        // Got to override it but it doesn't get called.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isDisabled() {
        return (boolean) getStateHelper().eval(PropertyKeys.disabled, false);
    }

    public void setDisabled(boolean disabled) {
        getStateHelper().put(PropertyKeys.disabled, disabled);
    }
}
