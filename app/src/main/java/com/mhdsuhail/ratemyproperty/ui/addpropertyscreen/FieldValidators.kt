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

            fun isCitySelectionValid(city: String, province: String, province2City : Map<String,List<String>>): Boolean {
               return province2City[province]?.any { it == city } ?: false
            }

            fun validateAddress(
                unitNumber: String,
                streetName: String,
                province: String,
                city: String,
                postalCode: String,
                province2CityMap: Map<String, List<String>>
            ): Boolean {
                return (isUnitNumberValid(unitNumber)
                        && isPostalCodeValid(postalCode)
                        && isCitySelectionValid(city, province, province2CityMap)
                        && isStreetNameValid(streetName))
            }
        }

    }
}