package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CircleTests.class, ContactTests.class, ContactsCircleRelationsTest.class })
public class AllTests {

}
