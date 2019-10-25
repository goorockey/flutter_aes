package com.startupcloud.aes.flutter_aes;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterAesPlugin */
public class FlutterAesPlugin implements MethodCallHandler {
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_aes");
    channel.setMethodCallHandler(new FlutterAesPlugin());
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if ("encrypt".equals(call.method)) {
        result.success(encrypt(call));
        return;
    }

    if ("decrypt".equals(call.method)) {
        result.success(decrypt(call));
        return;
    }

    return;
  }

  private String encrypt(MethodCall call) {
      try {
          String key = call.argument("key");
          String plainText = call.argument("plainText");
          return FlutterAesHandler.encrypt(key, plainText);
      } catch (Exception e) {
          return "";
      }
  }

  private String decrypt(MethodCall call) {
      try {
          String key = call.argument("key");
          String encryptedText = call.argument("encryptedText");
          return FlutterAesHandler.decrypt(key, encryptedText);
      } catch (Exception e) {
          e.printStackTrace();
          return "";
      }
  }
}

