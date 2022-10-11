package com.glancebar.junit;

import static org.junit.jupiter.api.condition.JRE.*;
import static org.junit.jupiter.api.condition.OS.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

/**
 * @author yishen.cai
 */
public class ConditionTestsDemo {
  @Test
  @EnabledOnOs(MAC)
  void onlyOnMacOs() {
    // ...
  }

  @TestOnMac
  void testOnMac() {
    // ...
  }

  @Test
  @EnabledOnOs({LINUX, MAC})
  void onLinuxOrMac() {
    // ...
  }

  @Test
  @DisabledOnOs(WINDOWS)
  void notOnWindows() {
    // ...
  }

  @Test
  @EnabledOnOs(architectures = "aarch64")
  void onAarch64() {
    // ...
  }

  @Test
  @DisabledOnOs(architectures = "x86_64")
  void notOnX86_64() {
    // ...
  }

  @Test
  @EnabledOnOs(value = MAC, architectures = "aarch64")
  void onNewMacs() {
    // ...
  }

  @Test
  @DisabledOnOs(value = MAC, architectures = "aarch64")
  void notOnNewMacs() {
    // ...
  }

  @Test
  @EnabledOnJre(JAVA_8)
  void onlyOnJava8() {
    // ...
  }

  @Test
  @EnabledOnJre({JAVA_9, JAVA_10})
  void onJava9Or10() {
    // ...
  }

  @Test
  @EnabledForJreRange(min = JAVA_9, max = JAVA_11)
  void fromJava9to11() {
    // ...
  }

  @Test
  @EnabledForJreRange(min = JAVA_9)
  void fromJava9toCurrentJavaFeatureNumber() {
    // ...
  }

  @Test
  @EnabledForJreRange(max = JAVA_11)
  void fromJava8To11() {
    // ...
  }

  @Test
  @DisabledOnJre(JAVA_9)
  void notOnJava9() {
    // ...
  }

  @Test
  @DisabledForJreRange(min = JAVA_9, max = JAVA_11)
  void notFromJava9to11() {
    // ...
  }

  @Test
  @DisabledForJreRange(min = JAVA_9)
  void notFromJava9toCurrentJavaFeatureNumber() {
    // ...
  }

  @Test
  @DisabledForJreRange(max = JAVA_11)
  void notFromJava8to11() {
    // ...
  }

  @Test
  @EnabledInNativeImage
  void onlyWithinNativeImage() {
    // ...
  }

  @Test
  @DisabledInNativeImage
  void neverWithinNativeImage() {
    // ...
  }

  @Test
  @EnabledIfSystemProperty(named = "os.arch", matches = ".*64.*")
  void onlyOn64BitArchitectures() {
    // ...
  }

  @Test
  @DisabledIfSystemProperty(named = "ci-server", matches = "true")
  void notOnCiServer() {
    // ...
  }

  @Test
  @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
  void onlyOnStagingServer() {
    // ...
  }

  @Test
  @DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
  void notOnDeveloperWorkstation() {
    // ...
  }

  @Test
  @EnabledIf("customCondition")
  void enabled() {
    // ...
  }

  @Test
  @DisabledIf("customCondition")
  void disabled() {
    // ...
  }

  boolean customCondition() {
    return true;
  }

  @Test
  @EnabledIf("com.glancebar.junit.ExternalCondition#customCondition")
  void enabledByClassLevelCondition() {
    // ...
  }
}

class ExternalCondition {

  static boolean customCondition() {
    return true;
  }
}
