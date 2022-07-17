package com.suremoon.game.kernel.data.map.impact_checker;

import com.suremoon.game.door.kernel.ImpactCheckerItf;

public class ImpactCheckerFactory implements ImpactCheckerFactoryItf {
  private ImpactCheckerFactory() {}

  ImpactCheckerFactoryItf factory = ImpactChecker::new;

  public void setFactory(ImpactCheckerFactoryItf itf) {
    factory = itf;
  }

  public static ImpactCheckerFactory Instance = new ImpactCheckerFactory();

  @Override
  public ImpactCheckerItf product() {
    return factory.product();
  }
}
