# Text Transformer

Transform RSS feed items as in the following description

The program takes a URL or file, transforms the text of them, then writes
it to a file or the standard output.
- Input A: A URL of RSS
- Input B: A CSV file
- Convert A: Trim each title and body if the length exceeds 10 characters.
- Convert B: Replace the specified words. e.g: from=uzabase, to=Uzabase, Inc.
- Output A: Print the result to the standard output
- Output B: Print the result to the specified file


## General idea of the solution

1. Parse the command line arguments.
   For example `java RssReader --input=http://tech.uzabase.com/ --convert=cut --output=result.txt`
   </br> where `--input` is a file name or URL `--convert` is a convert operation `--output` is a text file output
   </br> `input` is mandatory
   </br> `convert` is mandatory (allowing multiple operators)
   </br> `output` can be optional, default is standard output
2. If the `input` is URL, then read the data from that URL and parse it as RSS feed
   <br/>If the `input` is file, then read the data from that file and parse it as RSS feed
3. If the `convert` or `c` is "cut", then trim each title and body of the RSS item to length of 10 chars
   <br/> If the `convert` or `c` is "replace/a/b/", then replace each a by b in the title and body of the RSS item
   <br/> If the `convert` or `c` is "cut,convert", then replace each "uzabase" to "Uzabase, Inc." in the title and body of the RSS item and trim them by 10
4. If the `output` exists, then write the final result to the file.
   <br/>Otherwise write the final result to standard output

## Installation

Clone the project from git repository

## Usage

Just run the jar file

## Prerequisites

JDK 8/gradle/

Go to the cloned repo folder
```bash
# Build the project
.gradlew build
# Change dir
cd build/libs/
For help run
java -jar text-transformer-1.0-SNAPSHOT-all.jar setup-app --help # this will tell you how to use the app arguments
# Run the fat jar. Examples
java -jar text-transformer-1.0-SNAPSHOT-all.jar setup-app -i <input.txt>  -c <replace/aaa/bbb/> -o=<output.txt> # text input, text output
# or
java -jar text-transformer-1.0-SNAPSHOT-all.jar setup-app -i <http://example.com>  -c <replace/aaa/bbb/> -o=<output.txt> # URL input, text output
# etc
```

## How the app works

### Setup app command

The facade part of the app, parses/validates the arguments and controls the run 

### Command parser

Parses the commands (cut, replace, etc)

### Input/Output parser

Parses the inputs/outputs (file, url)

### Converter Command

Interface for the convert A / convert B

#### Text Converter

Implementation of converter based on converting strategies

#### Trim / Replace Strategy

Implementations of concrete convert strategies: cut or replace

### App Result Writer Impl

Implementation of result writer: writes to the file or standard output

## Libraries used

`com.github.rvesse:airline` for command-line arguments https://rvesse.github.io/airline/ <br/>
`com.apptastic:rssreader` to parse RSS https://github.com/w3stling/rssreader

## Example output

`java -jar text-transformer-1.0-SNAPSHOT-all.jar setup-app -i https://tech.uzabase.com/rss -c replace/UZABASE/TEST/`

```
Jestでテスト駆動開発（TDD）を実践してみようこんにちは！SPEEDA開発の岩見です。普段は業務でKotlinやClojureなどのJVM言語によく触れています。 今回TypeScriptを使ってテスト駆動開発（以下TDD）を実践する機会があり、良い勉強にめています。 これからTypeScriptやJestに触れる方、TypeScriptは使ったことがあるが自動テストやTDDにはあまり馴染みがない方などに読んでいただけると嬉しいなと思っています。 Jestの特徴を知る …NewsPicks AndroidアプリにPicturoidアプリケーションを開発するチームに所属しています。 レビューやお問い合わせなどからユーザーの皆様から導入を希望されておりましたPicture in Pictureを Androidアプリで実装するにあたって、苦労した点、工夫した点などを共有の挙動は、PictureInPictureParamsを使用して設定することが可能です。 Pi…Developers Summit 2021にスポンサー出展しました！みなさんこんにちは！UB Tech推進室にてインターンをしています、近藤です。 寒い冬が終わり日差しの強いりました。そのイベント振り返りレポートをお届けします。 Developers Summitとは Developers Summit（デベロッパーズサミット：通称デブサミ）は株式会社翔泳社主催の、日本最大級のソフトウェア開発者のためのカンファレンスです。ng におけるエンドポイントの列挙と IntelliJ IDEA の呼び出し階層による影響範囲調査こんにちは、 NewsPicks でソフトウェアエンジニアをしているガニエです。 2019年に NewsPicks に新卒入社してから現在に至るまで、基本的には to NewsPicks Enterprise を作るチームで開発をしています。 今回はそんな NewsPicks Enterprise の開発の過程で必要になった網羅的な影響範囲調査をできるかぎり系統的に行うためにしたことの話です。 背景 toC サービスの上に新たに…のロングポーリング設定でコストを99%削減した話こんにちは。 NewsPicksエンジニアの美濃部です。 NewsPicksではAWSをインフラ基盤として利用しているのですが、この記事では実際に行ったSQSのコスト削減の話をしたいと思います。 結コスト削減の余地がないかを検討していてSQSのコストが高すぎないかと感じたところから調査が始まりました。SQSはAPIリクエスト数に応じた従量課金なのですがAPIリクエスト数を確認するとやはり多すぎると感じました。（…Uzabase TecユーザベースでTech推進をしております西脇です。 本日、Tech blog は Uzabase Tech としてユーザベースの技術情報を発信するポータルにリニューアルしました。 このポータルでは、ユーザベースのエンジニアやデザイナー、クリエイタアルには、私たちの強い意思を乗せています。 以下はWho we areのコンテンツから引用となります。 未知なるWow!を開発しよう 「経済情報で…Vagrant で IE11 の Selenium Grid Node を作るこんにちは。SaaS Product Team の old_horiz動作する主要ブラウザについては、Selenium 公式の Docker Hub 等から Docker イメージを取得するだけで環境構築が完了します。 一方 Internet Explorer 11 (以下 IE11) でのテストは、他のブラウザと比較すると非常に手間がかかりま貯めたIstioのアクセスログを分析するツールをつくるこんにちは。SaaS Product Team SREの八代です。 はじめに 弊社が開発しているSPEEDAでは、KubernetesとIstioを利用してサービスメッシュ基盤を構築しています。オンプレミス上に構は、Istioを導入しているPodのアクセス分析を行う上での課題を共有するとともに、それを改善するための仕組みを作り始めたので、それについて書きますので、同じような問題を抱えている方の参考になれば嬉しいです。 ログの収集基…El生のときからは国語が苦手でテストもよく落ちましたが、一応中国語は分かります。 最近Product開発で中国語文章の全文検索について調べたことがありましたので、ここでElasticsearchの中国語Analyzerについて紹介したいと思います。 の文章を正しく形態素分析できるAnalyzerは不可欠だと思います。 Baiduで中国…億件オーダーのデータ移行ツールの検証の際に、確率計算とサンプリングを用いて効率的にテストをした話こんにちは。 NewsPicksエンジニアの鶴房です。 20のデータ移行ツールの検証の際に、サンプリングを用いて効率的にテストをした話をさせてもらいます。 何がしたかったのか NewsPicksでは、AWSのRedshiftを用いて、アクセスログやユーザーの行動ログの蓄積と解析を行っています。 解析 この…NewsPicksにCTOとして入社して1年でDX Criteriaを大幅改善した話こんにちは。このブログでは初めまして。2020年の2月にNewsPicksに入社した高山です。 今回は僕がNewsPicksのCTOになってからの1年でやったお仕事について書いての壮大な創業期の間にたくさんの新しい領域に挑戦しており、僕が入社したときには既に事業面でもシステム面でも「それなりの複雑さ」という感じでした。 前任CTOの杉浦さん（今はグループ内でアメリカでの新規サービスの立ち上げをし視をするはじめに こんにちは、TEST SREの鈴木(@sshota0809)です。 今回は、Grafana の Backend plugin という仕組みを利用して、データソースを BigQuery とした監視設定を行う方法を紹介します。 目次 はじめに 目次 TL;DR はじめにったこと Grafana Backend plugin を利用した BigQuery ベースの監視設定 Grafana と Backend plugin Grafana Backend plugin BigQuery プラグイン プラグインのインストール グラフの描画 アラート設定 ハマ…Spring Data R2DBCでリア SPEEDAではSpring Webfluxの採用が行われおり、一部リアクティブなシステムが動いています。 今回は、R2DBCという、リアクティブな非同期でRDBにするための仕様とSpring(Reactor Project) による実装およびサポートを利用して、APIののような特徴を持っています。 Reactive Streams specificationに基づ…Smalltalk かつ TDD で『オブジェクト指向設計実践ガイド』の「第5章 ダックタイピングでコストを削減する」をハンズオンしたら 9章も確認せざる得なかった今日は ダックを見逃す 問題を悪化させる ダックを見つける 概要としては、依存関係でがんじがらめになった設計を、ダックタイプを使って柔軟性のあるものに変える、というものです。 ハイライトだけ抜粋します。 ↓これが、 "依存しまくりのreparer prepa…Rustでモックオブジェクトを自作してみるこんにちは、SaaS Product Team の Ryo33 です。 この記事では Rust でモックオブジェクトを作ることを通してRefCellやMutex、Rc、Arcの使い方やSendやSyncについて学びます。 ます。 サンプルプログラム まず書いていきたいコードとして以下のようなcreate_user_with_nameというユースケースを考えます。 もし、名前が空文字でなければUserPort.storeを呼ぶというコードです。 #[derive(PartialEq, Eq, Debug大体半年が経ちました。 SaaS Product Teamに来るまでは、比較的ウォーターフォールがメインの現場にいたのですが、 ここにきて驚いたことのうち３つを書いていきたいと思います。 SaaS Product Teamってどんな開発をしているのだろうればと思います。 仕様書のドキュメントがない SaaS Product Teamでは、ドキュメントがほどんどありません。 アーキテクチャ図や、手順書のようなものはありますが、それもドキュメントの形式で残っているものではないで…ペアプログラム・プログラミング）をベースとしたチーム開発に取り組んでおり、ほぼ全ての作業をペアで行っています。*1 かく言う私もこのチームに入ってから 1 年以上の間 *2、日々ペアプログラミングに取り組む中でわかってきたことがあるので、トリームプログラミング』の「はじめに」には、以下の記述があります。 本書は、良いソフ…Smalltalkで『オブジェクト指向設計実践ガイド』の「第4章 柔軟なインタフェースをつくる」を考える今日は。 SPEEDA を開発している濱口です。んにインタフェースの可能性を探索できる、というのもあります。 今回は、著者の主張とは逸れますが、 テストコードを書きながらインタフェースの可能性を探索する、ということを試しました。 そこで、少し Smalltalk のコードが出て部の設計に注目してきました（単一責任の…Vue.jsでComposition APIを使ってクリーンアーキテクチャこんにちは！ Saas Product Teamの板倉です。 今回は少し前にバージョン3がリリースされたVue.jsとComposition APIを使ってクリーンア通りです。 Vue: 3.0.0 Typescript: 3.9.7 作成したコードはこちら 準備 まずはプロジェクトを作っていきましょう！ vue-cliを使って作っていきます。 vue-cliが入っていない方は yarn global add @vue/cli を実行してインス…WebCompついて調べてみたことを書きます。その1として、 カスタムエレメント に注目して書いています。ほかの項目については、また別の機会に書きます。 また、今回のサンプルコードと、デモです。 WebComponentsとは カスタムエレメント、STMLElementと同様にmy-web-componentsのように独自のコンポーネントを定義し、使うことができる仕組みです。 コ…ペアプログラミングではいつコードレビューするのか？こんにちは、SaaS Productチームの比嘉です。 私たちSaaS Productあるときエンジニアの友人から質問されました。 「ペアプログラミングではいつコードレビューするの？」 話を聞いてみると、その会社ではペアプロを導入し始めたのですが、従来から行なっているコードレビューと役割が重複しているこ 時世を鑑みてリモートペアプ…EnvoyをFront Proxyとして利用するこんにちは、ユーザベースのProductチームでSREをやっています阿南です。弊社ではKubebrnetes + Istioを利用してサービスメッシュの構築、マイクロサービスの運用を行っる設定について調べてみました。下記目次です。 EnvoyをFront Proxyとして利用するメリット Envoyのバージョン Front ProxyのためのEnvoy Configuration 80(HTTP),443(HTTPS)でアクセス…株式会社ユーザベース：Anthos GKE On-Prem でPEEDA』をハイブリッド クラウド化。開発・運用効率を大幅向上GoogleにおけるGCPにおいて、Anthos GKE On-Prem の事例紹介をして頂きました。 記事を見るKubernetes で運用する JVM アプリケーションの OutOfMemoryError に備えるこんr (以下 OOM) への対処です。 しかし実際に発生した際に、適切なオペレーションを行うのは意外と難しいのではないでしょうか。 特に本番環境では、まず再起動して復旧を急ぐことも多いかと思います。しかし、ただそれを繰り返すばかり集する仕組みを構築してみたいと思います。 環境構築 実際に…複雑性の高いプロダクトでも、ゆるぎないミッション・バリューが成長を支える――NewsPicksのプロダクト開発方針CodeZineにて、NewsPicksのプロダクト開発についての取材を受うことで、 初心者向けに簡単な使い方から、今回は主にUI部品・レイアウトについて紹介したいと思います！ Android Studioとは？ JetBrains社のIntellJ IDEAをベースとしたAndroidアプリ開発のための統合開発環境で、 Windows、Mac、roid.com 初期プロジェクト作成 インストールしたAndroid…Smalltalkで『オブジェクト指向設計実践ガイド』の「第3章 依存関係を管理する」をハンズオンしたら快適で楽しかった今日は。 SPEEDA を開発している濱口です。 前回の続きでSmalltalk に翻訳しながら読み進めることで、ただの写経をアクティブな学びにし、 いろいろな道草、発見をしながら楽しもう、というものです。 前回も触れましたが、やはり自分のコードとクラスライブラリの境界が無く、よいお手本がント設計で気をつけているn個のことはじめまして、昨年の12月に入社しました根岸です。 TESTに入社する前はフロントエンドエンジニアとして働いており、ここ1年間くらいはReactとTypeScriptの開発ばかりやっていました。 今回はフロン人 拡張性の高いコンポーネントを作りたいと思っている人 Propsの名前に一貫性をもたせる 例えばページ内でスタイルをあわせるためにButtonコンポーネントを作ったとしましょう。 Buttonコ…Mockitoを使ってDartでのTDDを加速させようartでTDD、そしてテストの独立性を担保していく上で欠かせないMockライブラリーのMockitoについて書こうと思います。 Mockitoとは Dart開発チームが作成している公式Mockライブラリーです。 名前の通りJavaにおいてメジャーなMockライましょう。Mockライブラリーを使ってテストを書いたこと…方法より原理 〜正規化ルールとリレーショナルモデルについて〜 【実践編】今日は。 SPEEDA を開発している濱口です。 理屈編では、まずリレーショナルデータベース（以下、RDしたテーブルに対し SELECT した結果もまたテーブル*1、 つまりは正規形であることが求められるため、 SELECT するたびにいちいちその結果について、 「第 n 正規化についてクリアだな…次に第 n + 1 正規化についてはどうかな…」などと、 ルールに当てはめてチェックしなければならないことを挙げました。 リレーショナルモデルの世界で、常に正しい処理結果を得る…
t
```

## Problems:

I don't have the strong validation rules against command-line args