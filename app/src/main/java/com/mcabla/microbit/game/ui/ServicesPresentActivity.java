package com.mcabla.microbit.game.ui;
/*
 * Author: Martin Woolley
 * Twitter: @bluetooth_mdw
 *
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.mcabla.microbit.game.Constants;
import com.mcabla.microbit.game.MicroBit;
import com.mcabla.microbit.game.R;
import com.mcabla.microbit.game.bluetooth.BleAdapterService;

import androidx.appcompat.app.AppCompatActivity;

public class ServicesPresentActivity extends AppCompatActivity {

    public static final int START_SERVICES_PRESENT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_present);
        TextView presence_generic_access = this.findViewById(R.id.service_generic_access_presence);
        TextView presence_generic_attribute = this.findViewById(R.id.service_generic_attribute_presence);
        TextView presence_device_information = this.findViewById(R.id.service_device_information_presence);
        TextView presence_accelerometer = this.findViewById(R.id.service_accelerometer_presence);
        TextView presence_button = this.findViewById(R.id.service_button_presence);
        TextView presence_led = this.findViewById(R.id.service_led_presence);

        indicatePresence(presence_generic_access,MicroBit.getInstance().hasService(BleAdapterService.GENERICACCESS_SERVICE_UUID));
        indicatePresence(presence_generic_attribute,MicroBit.getInstance().hasService(BleAdapterService.GENERICATTRIBUTE_SERVICE_UUID));
        indicatePresence(presence_device_information,MicroBit.getInstance().hasService(BleAdapterService.DEVICEINFORMATION_SERVICE_UUID));
        indicatePresence(presence_accelerometer,MicroBit.getInstance().hasService(BleAdapterService.ACCELEROMETERSERVICE_SERVICE_UUID));
        indicatePresence(presence_button,MicroBit.getInstance().hasService(BleAdapterService.BUTTONSERVICE_SERVICE_UUID));
        indicatePresence(presence_led,MicroBit.getInstance().hasService(BleAdapterService.LEDSERVICE_SERVICE_UUID));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Back");
    }

    private void indicatePresence(TextView tv , boolean present) {
        //Log.d(Constants.TAG, "indicatePresence: "+tv.getText().toString()+" "+present);
        if (present) {
            tv.setTextColor(Color.parseColor(Constants.SERVICE_PRESENT_COLOUR));
        } else {
            tv.setTextColor(Color.parseColor(Constants.SERVICE_ABSENT_COLOUR));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       // Log.d(Constants.TAG, "ServicesPresentActivity onOptionsItemSelected");
        switch (item.getItemId()) {
            case android.R.id.home:
                this.setResult(RESULT_OK);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
