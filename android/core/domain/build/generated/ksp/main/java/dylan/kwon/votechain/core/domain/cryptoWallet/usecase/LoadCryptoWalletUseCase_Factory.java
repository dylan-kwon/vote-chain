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
public final class LoadCryptoWalletUseCase_Factory implements Factory<LoadCryptoWalletUseCase> {
  private final Provider<CryptoWalletRepository> cryptoWalletRepositoryProvider;

  public LoadCryptoWalletUseCase_Factory(
      Provider<CryptoWalletRepository> cryptoWalletRepositoryProvider) {
    this.cryptoWalletRepositoryProvider = cryptoWalletRepositoryProvider;
  }

  @Override
  public LoadCryptoWalletUseCase get() {
    return newInstance(cryptoWalletRepositoryProvider.get());
  }

  public static LoadCryptoWalletUseCase_Factory create(
      Provider<CryptoWalletRepository> cryptoWalletRepositoryProvider) {
    return new LoadCryptoWalletUseCase_Factory(cryptoWalletRepositoryProvider);
  }

  public static LoadCryptoWalletUseCase newInstance(CryptoWalletRepository cryptoWalletRepository) {
    return new LoadCryptoWalletUseCase(cryptoWalletRepository);
  }
}
