SUMMARY = "A set of python modules for machine learning and data mining"
HOMEPAGE = "http://scikit-learn.org"

LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=c8d7e027b3e67a2b1fe7fe85ebeb17d7"

SRC_URI = "git://github.com/scikit-learn/scikit-learn.git;branch=0.24.X;protocol=https \
           file://0001-hack-around-numpy-get_include-to-force-looking-in-ta.patch \
           "
SRCREV = "15a949460dbf19e5e196b8ef48f9712b72a3b3c3"
S = "${WORKDIR}/git"

inherit setuptools3 pkgconfig python3-dir

export PYTHON_CROSSENV = "1"
export SKLEARN_BUILD_PARALLEL = "${@oe.utils.cpu_count()}"
export NPY_PKG_CONFIG_PATH = "${WORKDIR}/npy-pkg-config"
export NUMPY_INCLUDE_PATH = "${STAGING_DIR_TARGET}/usr/lib/python${PYTHON_BASEVERSION}/site-packages/numpy/core/include"

# Tell Numpy to look in target sysroot site-packages directory for libraries
LDFLAGS:append = " -L${STAGING_LIBDIR}/${PYTHON_DIR}/site-packages/numpy/core/lib"

do_compile:prepend() {
	echo "[ALL]" > ${S}/site.cfg
	echo "library_dirs = ${STAGING_LIBDIR}" >> ${S}/site.cfg
	echo "include_dirs = ${STAGING_INCDIR}" >> ${S}/site.cfg

	mkdir -p ${WORKDIR}/npy-pkg-config
	cp ${STAGING_DIR_TARGET}/usr/lib/python${PYTHON_BASEVERSION}/site-packages/numpy/core/lib/npy-pkg-config/* ${WORKDIR}/npy-pkg-config
	sed -i 's&prefix=${pkgdir}&prefix=${STAGING_DIR_TARGET}/usr/lib/python${PYTHON_BASEVERSION}/site-packages/numpy/core&g' ${WORKDIR}/npy-pkg-config/npymath.ini
	sed -i 's&prefix=${pkgdir}&prefix=${STAGING_DIR_TARGET}/usr/lib/python${PYTHON_BASEVERSION}/site-packages/numpy/core&g' ${WORKDIR}/npy-pkg-config/mlib.ini
}

DEPENDS += "python3-numpy-native python3-scipy-native python3-cython-native python3-numpy python3-scipy python3-cython"
RDEPENDS:${PN} += "python3-numpy python3-scipy python3-joblib python3-threadpoolctl python3-pytest"
