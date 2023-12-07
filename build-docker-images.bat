@echo off
setlocal enabledelayedexpansion

REM Collect all folders in the root directory into an array
set "folder_array="
for /D %%i in (*) do (
    set "folder_array=!folder_array! %%i"
)

REM Iterate through each item in the array
for %%j in (%folder_array%) do (
    set "service_name=%%j"

    REM Skip the root folder (if it matches the pattern)
    if not "!service_name!"=="." (
        echo Building and packaging !service_name! service...

        REM Navigate into the service directory
        pushd !service_name!

        REM Run Maven package (skipping tests)
        .\mvnw package -DskipTests

        REM Build Docker image with the service name
        docker build -t !service_name!-service .

        REM Navigate back to the root directory
        popd

        echo Finished building !service_name! service.
    )
)

echo All services built and packaged.
