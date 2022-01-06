DEPENDS += "openblas"
RDEPENDS_${PN} += "${PYTHON_PN}-multiprocessing openblas"

# Fix the distutils rpath pointing to an absolute ${libdir} path in ${TMPDIR}
do_install_append() {
    find ${D}${libdir}/${PYTHON_DIR}/site-packages/scipy/ -name '*.so' | xargs chrpath --delete
}
