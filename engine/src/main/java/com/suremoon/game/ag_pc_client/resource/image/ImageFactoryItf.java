package com.suremoon.game.ag_pc_client.resource.image;

public interface ImageFactoryItf {
  SMImage getSMImage(String name);

  SMImage reloadSMImage(String name);

  SMImage put(String name, SMImage smImage);

  ImageFactoryItf Null = new ImageFactoryNull();
}

class ImageFactoryNull implements ImageFactoryItf {
  SMImage Null = new SMImage();

  @Override
  public SMImage getSMImage(String path) {
    return Null;
  }

  @Override
  public SMImage reloadSMImage(String name) {
    return Null;
  }

  @Override
  public SMImage put(String name, SMImage smImage) {
    return Null;
  }
}
