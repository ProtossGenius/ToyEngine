package com.suremoon.game.ag_pc_client.resource.image;

import java.util.HashMap;
import java.util.Map;

/** Created by Water Moon on 2017/9/5. */
public class ImageFactory {
  private static ImageFactoryItf nullFactory = ImageFactoryItf.Null;
  private static ImageFactoryItf clientFactory = new ImageFactoryClient();
  private static ImageFactoryItf current = clientFactory;

  public static void DoNotUseImageLoader() {
    current = nullFactory;
  }

  public static void ResetFactory(ImageFactoryItf itf) {
    current = itf;
  }

  public static SMImage getSMImage(String name) {
    return current.getSMImage(name);
  }

  public static SMImage reloadSMImage(String name) {
    return current.reloadSMImage(name);
  }

  public static SMImage put(String name, SMImage smImage) {
    return current.put(name, smImage);
  }
}

class ImageFactoryClient implements ImageFactoryItf {
  private Map<String, SMImage> maps;
  private ImageLoader li = new ImageLoader();

  public ImageFactoryClient() {
    maps = new HashMap<>();
  }

  @Override
  public SMImage getSMImage(String name) {
    if (maps.containsKey(name)) {
      return maps.get(name);
    }
    return reloadSMImage(name);
  }

  @Override
  public SMImage reloadSMImage(String name) {
    SMImage smi = li.getSMImage(name);
    maps.put(name, smi);
    return smi;
  }

  @Override
  public SMImage put(String name, SMImage smImage) {
    maps.put(name, smImage);
    return smImage;
  }
}
