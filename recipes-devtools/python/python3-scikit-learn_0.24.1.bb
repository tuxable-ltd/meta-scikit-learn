SUMMARY = "A set of python modules for machine learning and data mining"
HOMEPAGE = "http://scikit-learn.org"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=c8d7e027b3e67a2b1fe7fe85ebeb17d7"

SRC_URI = "git://github.com/scikit-learn/scikit-learn.git;branch=0.24.X"
SRCREV = "c6512929fbee7232949c0f18cfb28cf3b5959df9"
S = "${WORKDIR}/git"

inherit setuptools3

export PYTHON_CROSSENV = "1"
export SKLEARN_BUILD_PARALLEL = "${@oe.utils.cpu_count()}"

DEPENDS += "python3-numpy-native python3-scipy-native python3-cython-native"
RDEPENDS_${PN} += "python3-numpy python3-scipy python3-joblib python3-threadpoolctl python3-pytest"
