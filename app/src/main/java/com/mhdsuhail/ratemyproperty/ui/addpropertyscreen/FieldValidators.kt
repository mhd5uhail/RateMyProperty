package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.util.Log

class FieldValidators {
    // Cannot be empty
    class Address {
        companion object {

            private const val TAG = "Address.FieldValidators"
            private const val postalCodeRegex = "^[ABCEGHJ-NPRSTVXY]\\d[ABCEGHJ-NPRSTV-Z][ -]?\\d[ABCEGHJ-NPRSTV-Z]\\d\$"

            fun isUnitNumberValid(num: String): Boolean {
                val ret = num.isNotBlank();
                Log.d(TAG, "unitNumber: $ret")
                return ret
            }

            fun isStreetNameValid(street: String): Boolean {
                val ret = street.isNotBlank();
                Log.d(TAG, "streetName: $ret")
                return ret
            }

            fun isPostalCodeValid(code: String): Boolean {
                val ret =
                    code.matches(Regex(postalCodeRegex))
                Log.d(TAG, "postalCode: $ret")
                return ret
            }
        }
    }
}