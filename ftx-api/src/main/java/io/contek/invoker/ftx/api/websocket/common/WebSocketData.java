package io.contek.invoker.ftx.api.websocket.common;

import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public abstract class WebSocketData {

  public String action;
  public long checksum;
  public double time;
}
