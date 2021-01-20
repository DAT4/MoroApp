package dtu.android.moroapp;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void testTest() {
        assertThat(2+2).isEqualTo(4);
        assertThat("abc").contains("a");
    }
}