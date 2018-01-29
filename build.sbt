lazy val root =
  project
    .in(file("."))
    .enablePlugins(ScalaJSPlugin)
    .settings(
      scalaVersion := "2.11.11",
      libraryDependencies ++= Seq(
        "org.akka-js" %%% "akkajsactortyped" % "1.2.5.9"
      ),
      fork in run := true,
      cancelable in Global := true,
      scalaJSUseMainModuleInitializer := true
    )
