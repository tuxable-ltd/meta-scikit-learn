inherit pypi setuptools3 native

SUMMARY = "SciPy: Scientific Library for Python"
HOMEPAGE = "https://www.scipy.org"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=8256119827cf2bbe63512d4868075867"

SRC_URI[md5sum] = "ecf5c58e4df1d257abf1634d51cb9205"
SRC_URI[sha256sum] = "ddae76784574cc4c172f3d5edd7308be16078dd3b977e8746860c76c195fa707"

DEPENDS = "${PYTHON_PN}-numpy-native ${PYTHON_PN}-pybind11-native lapack-native openblas-native"

CLEANBROKEN = "1"

S = "${WORKDIR}/scipy-${PV}"

export NPY_DISTUTILS_APPEND_FLAGS = "1"