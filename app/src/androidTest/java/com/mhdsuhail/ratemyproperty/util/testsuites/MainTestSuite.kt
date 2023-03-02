package com.mhdsuhail.ratemyproperty.util.testsuites

import com.mhdsuhail.ratemyproperty.database.FeaturesTests
import com.mhdsuhail.ratemyproperty.database.PropertyDetailsTests
import com.mhdsuhail.ratemyproperty.database.SearchTests
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(Suite::class)
@SuiteClasses(
PropertyDetailsTests::class, SearchTests::class, FeaturesTests::class
)
class MainTestSuite {

}