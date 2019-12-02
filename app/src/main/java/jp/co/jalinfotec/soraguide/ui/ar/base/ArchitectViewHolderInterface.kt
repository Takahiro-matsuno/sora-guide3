package jp.co.jalinfotec.soraguide.ui.ar.base

interface ArchitectViewHolderInterface {

    interface ILocationProvider {
        fun onResume()
        fun onPause()
    }
}