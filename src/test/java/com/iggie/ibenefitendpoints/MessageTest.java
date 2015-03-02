package com.iggie.ibenefitendpoints;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class MessageTest {

    private Message model = new Message();

    @Test
    public void test() throws Exception {
        assertThat(model, is(notNullValue()));
    }
}