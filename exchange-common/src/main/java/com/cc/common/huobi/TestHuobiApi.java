package com.cc.common.huobi;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;


public class TestHuobiApi {

  private HuobiApiRequestImpl impl = null;

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Before
  public void Initialize() {
    impl = new HuobiApiRequestImpl();
  }

  @Test
  public void testResult_Normal() {
    Map resultMap = new HashMap<String, Object>();

    }
}
