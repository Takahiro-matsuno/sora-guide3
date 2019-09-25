package jp.co.jalinfotec.soraguide.ar.base

interface ArchitectViewHolderInterface {

    interface ILocationProvider {
        fun onResume()
        fun onPause()
    }
}