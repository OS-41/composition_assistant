# 作曲アシスト（Android 版）

リズム・コード・メロディを編集しながら、音楽理論にもとづく提案やチェックを受けられる作曲支援ツールです。
理論の説明は主に [SoundQuest](https://soundquest.jp/) を参照し、見本タブの進行・リズムは Wikipedia、Berklee Online、UtaTen などのサイトも参照しています（アプリ内の「出典」タブに一覧があります）。

- 提案・チェック・分析はすべてアプリ内の JavaScript による規則ベースの処理で、AI やネット接続は使いません。
- 本体は `app/src/main/assets/index.html` の 1 ファイルです。claude.ai の Artifact 版と同じ内容です。

## インストール（Galaxy S25 など）

1. このリポジトリの **Releases** を開き、最新の `composition-assistant-<番号>.apk` をスマホでダウンロードします。
2. ダウンロードしたファイルを開きます。初回は「この提供元のアプリを許可」を求められるので、ブラウザ（または「マイファイル」）に許可を与えます。
3. 更新するときも同じ手順で上書きインストールできます（毎回同じ鍵で署名しているので、作業データは残ります）。

## ビルド

`main` に push すると GitHub Actions（`.github/workflows/build.yml`）が APK を作り、Releases と Actions の Artifacts に置きます。
手元でビルドする場合は Android Studio でこのフォルダを開き、`app` を実行してください。

## 構成

| パス | 内容 |
|---|---|
| `app/src/main/assets/index.html` | アプリ本体（HTML・CSS・JavaScript） |
| `app/src/main/java/jp/os41/compose/MainActivity.kt` | WebView での表示、外部リンクをブラウザで開く処理、ファイル保存、戻るボタン |
| `app/signing.keystore` | 個人配布用の署名鍵（上書き更新のため固定） |

## アプリ版だけの機能

- 「保存・読込」から JSON を「ダウンロード/作曲アシスト」に保存
- MIDI ファイルの書き出し（メロディ・コード・リズムの 3 トラック、ドラムは GM 配置）
