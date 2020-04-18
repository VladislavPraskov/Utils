package ${packageName}

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlinx.android.synthetic.main.${mainFragmentLayout}.*


class ${mainFragmentClass}: Fragment(){

    
    private val viewModel: ${viewModelClass} by viewModel()

    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.${mainFragmentLayout}, container, false)
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initViews()
        initListeners()
    }

    private fun initViews() {

    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer { state ->
            
        })
    }

    private fun initListeners() {

    }
}
