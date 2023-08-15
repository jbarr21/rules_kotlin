# Copyright 2018 The Bazel Authors. All rights reserved.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
load("@io_bazel_rules_kotlin//kotlin:core.bzl", "define_kt_toolchain", "kt_ksp_plugin")
load("@bazel_skylib//rules:build_test.bzl", "build_test")
load("@rules_java//java:defs.bzl", "java_binary", "java_plugin")
load("@io_bazel_rules_kotlin//kotlin:jvm.bzl", "kt_jvm_library")

package(default_visibility = ["//visibility:public"])

define_kt_toolchain(name = "kotlin_toolchain")

java_plugin(
    name = "autoservice",
    generates_api = True,
    processor_class = "com.google.auto.service.processor.AutoServiceProcessor",
    deps = ["@maven//:com_google_auto_service_auto_service"],
)

kt_ksp_plugin(
    name = "coffee_proc_ksp_plugin",
    processor_class = "coffee.proc.CoffeeProcessorProvider",
    deps = [
        ":coffee_proc",
        "@maven//:com_google_code_gson_gson",
        "@maven//:com_google_devtools_ksp_symbol_processing_api",
    ],
)

kt_jvm_library(
    name = "coffee_proc",
    srcs = glob([
        "proc/*.kt",
        "proc/*.java",
    ]),
    plugins = [
        "//:autoservice",
    ],
    deps = [
        "@maven//:com_google_auto_service_auto_service_annotations",
        "@maven//:com_google_code_gson_gson",
        "@maven//:com_google_devtools_ksp_symbol_processing_api",
    ],
)

kt_jvm_library(
    name = "coffee_lib",
    srcs = glob([
        "*.kt",
        "*.java",
    ]),
    plugins = [
        "//:coffee_proc_ksp_plugin",
    ],
    deps = [
        "@maven//:com_google_code_gson_gson",
    ],
)

java_binary(
    name = "coffee_app",
    main_class = "coffee.CoffeeApp",
    visibility = ["//visibility:public"],
    runtime_deps = [":coffee_lib"],
)

build_test(
    name = "force_build_app_test",
    targets = [
        "//:coffee_app",
        # build_test doesn't actually fail unless you force the deploy jar to be built
        "//:coffee_app_deploy.jar",
    ],
)
