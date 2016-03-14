package gwt.material.design.addins.client.pathanimator;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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


import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Created by Mark Kevin on 3/13/2016.
 */
interface MaterialPathAnimatorClientBundle extends ClientBundle {

    MaterialPathAnimatorClientBundle INSTANCE = GWT.create(MaterialPathAnimatorClientBundle.class);

    @Source("resources/js/cta.min.js")
    TextResource pathanimatorJs();

    @Source("resources/js/cta.js")
    TextResource pathanimatorDebugJs();
}