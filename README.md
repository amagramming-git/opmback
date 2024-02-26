# OPEN-MEMO

現時点では単なるメモアプリです。
今後拡張させることを考えています。

## open-memo(URL)

[https://stg.open-memo.com](https://stg.open-memo.com)

※ 上記ステージング環境です。本番環境は作成中です。また費用削減のため、現在公開しておりません。

## 関連する Github

こちらのサービスに関係する Github のリンクを貼り付けます。

[フロントエンド(https://github.com/amagramming-git/opmfront)](https://github.com/amagramming-git/opmfront)

バックエンド(当リポジトリ)

[インフラ(https://github.com/amagramming-git/opminfra)](https://github.com/amagramming-git/opminfra)

## サービス作成の背景

比較的モダンな技術を用いて、基本機能を一通り持っているアプリケーションを作成し、今後の雛形としたいという思いで作成しました。

### フロントエンドの使用技術と実装した機能のポイント

- React + Next.js による SPA
- Typescript によるコーディング
- Redux による状態管理
- Bootstrap によるレスポンシブデザイン
- ログイン・ログアウトによる画面表示の切り替え
- CRUD 操作
- バリデーション
- ページング機能
- Rails のフラッシュ的なアラート表示

### バックエンドの使用技術と実装した機能のポイント

- Java を用いた実装
- SpringBoot + SpringSecurity による実装
- MyBatis による DB 操作
- docker-compose による開発環境の整備
- Devcontainer による開発環境の整備 (Docker に対して SSH 的に接続して VSCode で開発が行えるもの)
- ログイン・ログアウト機能
- CRUD 操作
- ページング機能
- SpringBootActuator によるヘルスチェック画面の作成

### バックエンドの使用技術と実装した機能のポイント

- AWS を用いた基盤構築
- Docker コンテナによる基盤構築(ECS)
- Cloudformation を使用した Terraform 実行環境の整備
- Terraform を使用した IaC による構築

## サービス概要

こちらは単なるメモアプリです。

Email を用いてユーザ登録を行い、メモの追加・参照・更新・削除ができます。

## 機能一覧

以下に実装した機能の一覧を記載致します。

### ユーザ登録機能

Email,ユーザ名,パスワードを入力してユーザの登録を行うことができます。(※今後の展望 メールアドレス確認機能野追加 )

### ユーザログイン機能及びログアウト機能

ユーザログイン機能にて JWT トークンを取得し、Cookie に設定することで、以降ログイン時の画面となり操作可能です。

ログアウト機能では Cookie の JWT トークンを削除することでログアウトとなります。

現在はログイン時には DB にハッシュ化されたパスワードを保存する形式で Basic 認証を行っています。(※今後の展望 Cognito 等の連携 )

### メモの追加・参照・更新・削除

メモの追加・参照・更新・削除の一連の操作が可能です。

参照時には一覧画面にてページング機能を実装しています。

(※今後の展望 追加機能の開発 )

## 使用技術

### フロントエンド

React,Next.js,Redux,Typescript,Bootstrap (各バージョンはリポジトリ参照)

### バックエンド

Java,SpringBoot,SpringSecurity,MyBatis,MySQL,SpringBootActuator,Docker

### インフラ

AWS,Cloudformation,Terraform,ECS,Docker
