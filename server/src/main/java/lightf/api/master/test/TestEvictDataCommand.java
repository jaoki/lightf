package lightf.api.master.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.hadoop.hdfs.protocol.proto.ClientNamenodeProtocolProtos.ClientNamenodeProtocol;
import org.apache.hadoop.hdfs.protocol.proto.ClientNamenodeProtocolProtos.GetFsStatsResponseProto;
import org.apache.hadoop.hdfs.protocol.proto.ClientNamenodeProtocolProtos.GetFsStatusRequestProto;

import com.google.protobuf.Descriptors.MethodDescriptor;
import com.google.protobuf.Message;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcChannel;
import com.google.protobuf.RpcController;

@Path("/test/evict")
public class TestEvictDataCommand {
	
	public class DummyRpcCallBack implements RpcCallback<GetFsStatsResponseProto> {

		@Override
		public void run(GetFsStatsResponseProto parameter) {
			System.out.println("parameter.getMissingBlocks():" + parameter.getMissingBlocks());
		}

	}

	public class DummyRpcController implements RpcController {
		@Override public void reset() { }
		@Override public boolean failed() { return false; }
		@Override public String errorText() { return null; }
		@Override public void startCancel() { }
		@Override public void setFailed(String reason) { }
		@Override public boolean isCanceled() { return false; }
		@Override public void notifyOnCancel(RpcCallback<Object> callback) { }
	}

	@XmlRootElement
	public static class EvictResult {

	}

	@GET
	public EvictResult get(){
		// TODO do something with HDFS
		GetFsStatusRequestProto.Builder fsStatusRequestBuilder = GetFsStatusRequestProto.newBuilder();
		GetFsStatusRequestProto request = fsStatusRequestBuilder.build();
		RpcChannel channel = new LightFRpcChannel();
		ClientNamenodeProtocol service = ClientNamenodeProtocol.newStub(channel);
		RpcController controller = new DummyRpcController();
		RpcCallback<GetFsStatsResponseProto> done = new DummyRpcCallBack();
		service.getFsStats(controller, request, done);
		return new EvictResult();
		
	}
	
	public static class LightFRpcChannel implements RpcChannel{


		@Override
		public void callMethod(
					MethodDescriptor method,
					RpcController controller,
					Message request,
					Message responsePrototype,
					RpcCallback<Message> done) {

			Socket socket = null;
			DataOutputStream out = null;
			DataInputStream in = null;

			try {
				String namenodeHostAddress = "10.103.217.35";
				socket = new Socket(namenodeHostAddress, 8020);
				out = new DataOutputStream(socket.getOutputStream());
				in = new DataInputStream(socket.getInputStream());
				byte[] requestData = request.toByteArray();

				int requestLength = requestData.length;
				out.writeInt(requestLength);
				out.write(requestData, 0, requestLength);
				out.flush();
				
				int responseDataLength = in.readInt();
				byte[] responseData = new byte[responseDataLength];
				in.read(responseData, 0, responseDataLength);
				
				GetFsStatsResponseProto response = GetFsStatsResponseProto.parseFrom(responseData);
				done.run(response);

			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					if(socket != null){
						socket.close();
					}
					if(in != null){
						in.close();
					}
					if(out != null){
						out.close();
					}
				} catch (IOException e) {}
			}
		}
	}

}
