package dylan.kwon.votechain.core.domain.cryptoWallet.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dylan.kwon.votechain.core.domain.cryptoWallet.port.CryptoWalletRepository;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class CreateMnemonicUseCase_Factory implements Factory<CreateMnemonicUseCase> {
  private final Provider<CryptoWalletRepository> cryptoWalletRepositoryProvider;

  public CreateMnemonicUseCase_Factory(
      Provider<CryptoWalletRepository> cryptoWalletRepositoryProvider) {
    this.cryptoWalletRepositoryProvider = cryptoWalletRepositoryProvider;
  }

  @Override
  public CreateMnemonicUseCase get() {
    return newInstance(cryptoWalletRepositoryProvider.get());
  }

  public static CreateMnemonicUseCase_Factory create(
      Provider<CryptoWalletRepository> cryptoWalletRepositoryProvider) {
    return new CreateMnemonicUseCase_Factory(cryptoWalletRepositoryProvider);
  }

  public static CreateMnemonicUseCase newInstance(CryptoWalletRepository cryptoWalletRepository) {
    return new CreateMnemonicUseCase(cryptoWalletRepository);
  }
}
