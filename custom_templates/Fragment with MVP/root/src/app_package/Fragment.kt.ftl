package ${packageName}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import android.support.v4.app.Fragment
import javax.inject.Inject
import kotlinx.android.synthetic.main.${mainFragmentLayout}.*

class ${mainFragmentClass}: DaggerFragment(),${prefixName}View
{
    @Inject 
    lateinit var presenter: ${prefixName}Presenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.${mainFragmentLayout}, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(SOME_ARG, 0)
        initViews()
        initListeners()
    }

    private fun initViews() {

    }

    private fun initListeners() {

    }
	
    override fun progress(isProgress: Boolean) {

    }

companion object {
        const val SOME_ARG = "some_argument"
        fun getInstance(someInt: Int):${mainFragmentClass} {
            val myFragment = ${mainFragmentClass}()
            val args = Bundle()
            args.putInt(SOME_ARG, someInt)
            myFragment.arguments = args
            return myFragment
        }
    }
    
    
}
