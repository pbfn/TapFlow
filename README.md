# TapFlow

## Rodando no Desktop (KMP)

O projeto agora possui o módulo `:composeApp` com Compose Multiplatform.

### Pré-requisitos
- JDK 17+
- Gradle Wrapper (`./gradlew`)

### Executar desktop
```bash
./gradlew runDesktop
```

Comando equivalente direto no módulo:
```bash
./gradlew :composeApp:run
```

## iOS (framework)
Para gerar o framework iOS (para usar no Xcode):

```bash
./gradlew :composeApp:assemble
```

Os binários de framework são gerados para:
- `iosX64`
- `iosArm64`
- `iosSimulatorArm64`
