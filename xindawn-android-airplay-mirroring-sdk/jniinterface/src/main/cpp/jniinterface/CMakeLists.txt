cmake_minimum_required(VERSION 3.4.1)
include_directories (./)


add_library(
            jniinterface
            SHARED
   					com_xindawn_jni_PlatinumJniProxy.cpp 
            )

set(libdir ${PROJECT_SOURCE_DIR}/mediaserver)


target_link_libraries(jniinterface log ${libdir}/libmediaserver.so android)
