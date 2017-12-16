lazy val root =
  project
    .in(file("."))
    .enablePlugins(ScalaJSPlugin)
    .settings(
      scalaVersion := "2.12.4",
      libraryDependencies ++= Seq(
        "org.akka-js" %%% "akkajsactortyped" % "1.2.5.8-typed-SNAPSHOT"
      ),
      fork in run := true,
      cancelable in Global := true,
      scalaJSUseMainModuleInitializer := true
    )
