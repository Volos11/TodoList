package com.example.notes.fragments


//noinspection SuspiciousImport
import android.R
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.databinding.NoteListBinding
import com.example.notes.fragments.viewmodel.GetNoteViewModel
import com.tetron.presentaton.adapters.AllNotesRecyclerAdapter
import com.tetron.presentaton.interfaces.activity.Navigation
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class NoteListFragments : Fragment() {

    private val viewModel: GetNoteViewModel by viewModel<GetNoteViewModel>()

    private val binding: NoteListBinding by lazy {
        NoteListBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View = binding.root


    @SuppressLint("MissingPermission")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
// Создаем канал уведомлений


        requireActivity().let {
            if (it is Navigation) it.show_bar()
        }
        // Create the NotificationChannel.
        val name = getString(R.string.search_go)
        val descriptionText = getString(R.string.ok)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val mChannel = NotificationChannel("my_channel_01", name, importance)
        mChannel.description = descriptionText
        // Register the channel with the system. You can't change the importance
        // or other notification behaviors after this.
        val notificationManager =
            context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(mChannel)

        binding.header.settings.visibility=View.VISIBLE

        binding.header.settings.setOnClickListener {
            BottomSheetDialogOnTask( )
                .show(requireActivity().supportFragmentManager, "Dialog")



        }
        binding.header.headerTitle.text = "Заметки"
        binding.header.headerButtonAction.setOnClickListener {
            requireActivity().let {
                if (it is Navigation) it.main_addfragment(NoteFragment())
            }
        }

        fun addfragment(id: Long?) {
            requireActivity().let {
                if (it is Navigation) it.main_addfragment(EditNote.newInstance(id))
            }
        }

        val adapterRecycler: AllNotesRecyclerAdapter by lazy {

            AllNotesRecyclerAdapter(onClickItem = {
                addfragment(it.id)
            }, onholdClickItem = {

                lifecycleScope.launch {
                    viewModel.deleteNote(it)
                }
                requireActivity().let {
                    if (it is Navigation) it.main_addfragment(NoteListFragments())
                }

            })

        }



        viewModel.GetallNote()


        binding.recyclerTasks.adapter = adapterRecycler



        viewModel.dataListOfNote.observe(viewLifecycleOwner) { listOfTasks ->
            adapterRecycler.listOfTasks = listOfTasks
            changeVisibility(listOfTasks.isEmpty())
        }
        binding.recyclerTasks.layoutManager = LinearLayoutManager(requireContext())


        var builder = NotificationCompat.Builder(requireContext(), "my_channel_01")
            .setSmallIcon(R.drawable.sym_def_app_icon).setContentTitle("textTitle")
            .setContentText("textContent").setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)



        notificationManager.createNotificationChannel(mChannel)


//  УВЕДОМЛЕНИЯ
//        with(NotificationManagerCompat.from(requireContext())) {
//            if (ActivityCompat.checkSelfPermission(
//                    requireActivity(),
//                    POST_NOTIFICATIONS
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                // TODO: Consider calling
//                // ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                // public fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
//                //                                        grantResults: IntArray)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//
//                return@with
//            }
//            // notificationId is a unique int for each notification that you must define.
//            notify(1, builder.build())
//        }


    }

    private fun changeVisibility(flagListEmpty: Boolean) {
        binding.recyclerTasks.visibility = if (flagListEmpty) View.GONE else View.VISIBLE

    }


}