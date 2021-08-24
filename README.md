meta-scikit-learn
=================

This layer contains recipes for building scikit-learn and depenencies in the
OpenEmbedded build system.

## Configurations Tested

* armv8
* armv7

## Dependencies

This layer depends on:

*  [meta-oe][]
*  [meta-python][]
*  [meta-scipy][]

[meta-oe]: https://layers.openembedded.org/layerindex/branch/master/layer/meta-oe/
[meta-python]: https://layers.openembedded.org/layerindex/branch/master/layer/meta-python/
[meta-scipy]: https://github.com/gpanders/meta-scipy

## Using this layer

Clone this repository with the tag or branch corresponding to your OpenEmbedded
version and add it to your workspace. For example, if using `gatesgarth`:

    git clone -b master https://github.com/tuxable-ltd/meta-scikit-learn
    bitbake-layers add-layer meta-scikit-learn

You will also need to enable FORTRAN support by adding the following to your
`local.conf` file:

    FORTRAN:forcevariable = ",fortran"
    RUNTIMETARGET:append:pn-gcc-runtime = " libquadmath"
    HOSTTOOLS += "gfortran"

If you're using a custom distribution, you can alternatively include the two
above lines in your `distro.conf` file.

## Contributing to this layer

To report bugs or request new recipes & features please use our [issue
tracker][1].

To submit changes to this layer please fork the repository on GitHub and open
a [pull request][2].

[1]: https://github.com/tuxable-ltd/meta-scikit-learn/issues
[2]: https://github.com/tuxable-ltd/meta-scikit-learn/pulls
