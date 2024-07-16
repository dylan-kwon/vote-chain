package dylan.kwon.votechain.core.domain.cryptoWallet.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class ValidateMnemonicUseCase_Factory implements Factory<ValidateMnemonicUseCase> {
  @Override
  public ValidateMnemonicUseCase get() {
    return newInstance();
  }

  public static ValidateMnemonicUseCase_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ValidateMnemonicUseCase newInstance() {
    return new ValidateMnemonicUseCase();
  }

  private static final class InstanceHolder {
    private static final ValidateMnemonicUseCase_Factory INSTANCE = new ValidateMnemonicUseCase_Factory();
  }
}
