package yeniellandestoy.native_shared_preferences;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.firebase.iid.Registrar;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.PluginRegistry;

/** NativeNativeSharedPreferencesPlugin */
public class NativeSharedPreferencesPlugin implements FlutterPlugin {
  private static final String CHANNEL_NAME = "native_shared_preferences";
  private MethodChannel channel;

  // public static void registerWith(@NonNull PluginRegistry.Registrar registrar) {
  //   final NativeSharedPreferencesPlugin plugin = new NativeSharedPreferencesPlugin();
  //   plugin.setupChannel(registrar.messenger(), registrar.context());
  // }

  @Override
  public void onAttachedToEngine(@NonNull FlutterPlugin.FlutterPluginBinding binding) {
    setupChannel(binding.getBinaryMessenger(), binding.getApplicationContext());

  }

  @Override
  public void onDetachedFromEngine(FlutterPlugin.FlutterPluginBinding binding) {
    teardownChannel();
  }

  private void setupChannel(BinaryMessenger messenger, Context context) {
    channel = new MethodChannel(messenger, CHANNEL_NAME);
    MethodCallHandlerImpl handler = new MethodCallHandlerImpl(context);
    channel.setMethodCallHandler(handler);
  }

  private void teardownChannel() {
        if (channel != null) {
      channel.setMethodCallHandler(null);
      channel = null;
    }
  }
}
