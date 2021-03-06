package io.contek.invoker.coinbasepro.api.websocket;

import com.google.common.collect.ImmutableList;
import io.contek.invoker.commons.api.actor.IActor;
import io.contek.invoker.commons.api.actor.ratelimit.RateLimitQuota;
import io.contek.invoker.commons.api.actor.security.ICredential;
import io.contek.invoker.commons.api.websocket.BaseWebSocketApi;
import io.contek.invoker.commons.api.websocket.IWebSocketAuthenticator;
import io.contek.invoker.commons.api.websocket.WebSocketCall;
import io.contek.invoker.commons.api.websocket.WebSocketContext;

import javax.annotation.concurrent.ThreadSafe;

import static io.contek.invoker.coinbasepro.api.ApiFactory.RateLimits.ONE_WEB_SOCKET_CONNECTION;

@ThreadSafe
public abstract class WebSocketApi extends BaseWebSocketApi {

  private final WebSocketContext context;

  protected WebSocketApi(IActor actor, WebSocketContext context) {
    super(actor, WebSocketMessageParser.getInstance(), IWebSocketAuthenticator.noOp());
    this.context = context;
  }

  @Override
  protected ImmutableList<RateLimitQuota> getRequiredQuotas() {
    return ONE_WEB_SOCKET_CONNECTION;
  }

  @Override
  protected WebSocketCall createCall(ICredential credential) {
    return WebSocketCall.fromUrl(context.getBaseUrl());
  }
}
