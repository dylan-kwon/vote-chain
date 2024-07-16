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
public final class SaveNewCryptoWalletUseCase_Factory implements Factory<SaveNewCryptoWalletUseCase> {
  private final Provider<CryptoWalletRepository> cryptoWalletRepositoryProvider;

  public SaveNewCryptoWalletUseCase_Factory(
      Provider<CryptoWalletRepository> cryptoWalletRepositoryProvider) {
    this.cryptoWalletRepositoryProvider = cryptoWalletRepositoryProvider;
  }

  @Override
  public SaveNewCryptoWalletUseCase get() {
    return newInstance(cryptoWalletRepositoryProvider.get());
  }

  public static SaveNewCryptoWalletUseCase_Factory create(
      Provider<CryptoWalletRepository> cryptoWalletRepositoryProvider) {
    return new SaveNewCryptoWalletUseCase_Factory(cryptoWalletRepositoryProvider);
  }

  public static SaveNewCryptoWalletUseCase newInstance(
      CryptoWalletRepository cryptoWalletRepository) {
    return new SaveNewCryptoWalletUseCase(cryptoWalletRepository);
  }
}
