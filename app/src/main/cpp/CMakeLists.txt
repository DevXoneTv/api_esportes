cmake_minimum_required(VERSION 3.18.1)

project("api_esportes")

# 🔥 Informar que vamos usar C++17
set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

add_library(
        api_esportes
        SHARED
        api_esportes.cpp
)

find_library(
        log-lib
        log
)

# 🔥 Aqui linkamos a STL do Android também
target_link_libraries(
        api_esportes
        ${log-lib}
        c++_shared
)
