package dylan.kwon.votechain.core.domain.auth.usecase;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dylan.kwon.votechain.core.domain.auth.port.AuthRepository;
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
public final class SubmitSimplePasswordUseCase_Factory implements Factory<SubmitSimplePasswordUseCase> {
  private final Provider<AuthRepository> authRepositoryProvider;

  public SubmitSimplePasswordUseCase_Factory(Provider<AuthRepository> authRepositoryProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public SubmitSimplePasswordUseCase get() {
    return newInstance(authRepositoryProvider.get());
  }

  public static SubmitSimplePasswordUseCase_Factory create(
      Provider<AuthRepository> authRepositoryProvider) {
    return new SubmitSimplePasswordUseCase_Factory(authRepositoryProvider);
  }

  public static SubmitSimplePasswordUseCase newInstance(AuthRepository authRepository) {
    return new SubmitSimplePasswordUseCase(authRepository);
  }
}
