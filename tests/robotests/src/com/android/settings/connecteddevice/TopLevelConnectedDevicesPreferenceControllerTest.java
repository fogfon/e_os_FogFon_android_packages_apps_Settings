/*
 * Copyright (C) 2018 The Android Open Source Project
 *
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
 */

package com.android.settings.connecteddevice;

import static com.google.common.truth.Truth.assertThat;

import android.content.Context;

import com.android.settings.R;
import com.android.settings.testutils.SettingsRobolectricTestRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;

@RunWith(SettingsRobolectricTestRunner.class)
public class TopLevelConnectedDevicesPreferenceControllerTest {

    private Context mContext;
    private TopLevelConnectedDevicesPreferenceController mController;

    @Before
    public void setUp() {
        mContext = RuntimeEnvironment.application;
        mController = new TopLevelConnectedDevicesPreferenceController(mContext, "test_key");
    }

    @Test
    @Config(shadows = ShadowAdvancedConnectedDeviceController.class)
    public void getSummary_shouldCallAdvancedConnectedDeviceController() {
        assertThat(mController.getSummary())
                .isEqualTo(mContext.getText(R.string.settings_label_launcher));
    }

    @Implements(AdvancedConnectedDeviceController.class)
    private static class ShadowAdvancedConnectedDeviceController {

        @Implementation
        public static int getConnectedDevicesSummaryResourceId(Context context) {
            return R.string.settings_label_launcher;
        }
    }
}