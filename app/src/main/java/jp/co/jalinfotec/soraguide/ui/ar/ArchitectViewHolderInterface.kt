package jp.co.jalinfotec.soraguide.ui.ar

interface ArchitectViewHolderInterface {

    interface ILocationProvider {
        fun onResume()
        fun onPause()
    }
}